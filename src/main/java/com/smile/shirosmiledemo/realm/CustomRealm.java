package com.smile.shirosmiledemo.realm;

import com.smile.shirosmiledemo.common.LoginToken;
import com.smile.shirosmiledemo.entity.User;
import com.smile.shirosmiledemo.repository.UserRepository;
import com.smile.shirosmiledemo.utils.JWTUtil;
import com.smile.shirosmiledemo.utils.Md5Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 11:45
 * @description：
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(CustomRealm.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof LoginToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("————身份认证方法————");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        List<User> userList = userRepository.findByUserName(username);
        if (CollectionUtils.isEmpty(userList)) {
            throw new AccountException("用户名或密码不正确");
        }

        String orginPassword = new String((char[]) token.getCredentials());
        User user = userList.get(0);
        String salt = user.getSalt();
        String encodedPassword = Md5Utils.md5Encrypt(orginPassword + salt);
        if (!Objects.equals(encodedPassword, user.getUserPassword())) {
            throw new AccountException("用户名或密码不正确");
        }

        return new SimpleAuthenticationInfo(user.getUserName(), orginPassword, ByteSource.Util.bytes(salt), getName());


//        String token = (String) authenticationToken.getCredentials();
////        // 解密获得username，用于和数据库进行对比
////        String username = JWTUtil.getUsername(token);
////        if (username == null) {
////            throw new AuthenticationException("token invalid");
////        }
////        // 从数据库获取对应用户名密码的用户
////        List<User> userList = userRepository.findByUserName(username);
////        if (null == userList || userList.size() == 0) {
////            throw new AccountException("用户名或密码不正确");
////        }
////        User user = userList.get(0);
////        if (user.getStatus().equals("disable")) {
////            throw new AuthenticationException("该用户已被停用！");
////        }
////
////        if (!JWTUtil.verify(token, username, Constract.SECRET)) {
////            throw new AuthenticationException("用户名或密码不正确");
////        }
////        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        List<User> userList = userRepository.findByUserName(username);
        String role = userList.get(0).getUserType();
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

}
