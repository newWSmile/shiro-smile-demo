package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.common.Constract;
import com.smile.shirosmiledemo.dto.SmileReturnObject;
import com.smile.shirosmiledemo.dto.UserDto;
import com.smile.shirosmiledemo.entity.User;
import com.smile.shirosmiledemo.repository.UserRepository;
import com.smile.shirosmiledemo.utils.JWTUtil;
import com.smile.shirosmiledemo.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:14
 * @description：
 */
@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public SmileReturnObject notLogin() {
        return SmileReturnObject.success("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public SmileReturnObject notRole() {
        return SmileReturnObject.success("您没有权限！");
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public SmileReturnObject logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return SmileReturnObject.success("成功注销！");
    }


    /**
     * 登陆
     *
     * @param userDto
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public SmileReturnObject login(UserDto userDto) {
        //根据权限，指定返回数据
        List<User> userList = userRepository.findByUserName(userDto.getUserName());
        if (null == userList || userList.size() == 0) {
            throw new AccountException("用户名或密码不正确");
        }
        User user = userList.get(0);
        String salt = user.getSalt();
        String userDtoPassword = userDto.getUserPassword();

        if (!user.getUserPassword().equals(Md5Utils.md5Encrypt(userDtoPassword+salt))){
            throw new AccountException("用户名或密码不正确");
        }
        return SmileReturnObject.success(JWTUtil.sign(userDto.getUserName(), Constract.SECRET));
    }


    @RequestMapping(path = "/unauthorized/{message}")
    public SmileReturnObject unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        SmileReturnObject<String> returnObject = SmileReturnObject.success(message);
        returnObject.setCode("401");
        return returnObject;
    }
}
