package com.smile.shirosmiledemo.common;

import com.smile.shirosmiledemo.entity.User;
import com.smile.shirosmiledemo.repository.UserRepository;
import com.smile.shirosmiledemo.utils.SpringKit;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 14:16
 * @description：
 */
public class LoginCredentialsMatcher extends HashedCredentialsMatcher {

    private UserRepository userRepository = SpringKit.getBean(UserRepository.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        LoginToken loginToken = (LoginToken) token;
        return loginToken.isAuto() ? checkJwt(token, info) : super.doCredentialsMatch(token, info);
    }

    private boolean checkJwt(AuthenticationToken token, AuthenticationInfo info) {
        String userName = token.getPrincipal().toString();
        List<User> userList = userRepository.findByUserName(userName);
        if (CollectionUtils.isEmpty(userList)) {
            throw new UnknownAccountException();
        }
        return true;
    }
}
