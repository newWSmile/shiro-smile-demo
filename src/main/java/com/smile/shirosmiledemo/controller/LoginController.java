package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.common.Constract;
import com.smile.shirosmiledemo.common.LoginToken;
import com.smile.shirosmiledemo.dto.SmileReturnObject;
import com.smile.shirosmiledemo.dto.UserDto;
import com.smile.shirosmiledemo.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:14
 * @description：
 */
@RestController
public class LoginController {


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
        LoginToken token = new LoginToken((userDto.getUserName()),userDto.getUserPassword());
        SecurityUtils.getSubject().login(token);
        return SmileReturnObject.success(JWTUtil.sign(userDto.getUserName(), Constract.SECRET));
    }


    @RequestMapping(path = "/unauthorized/{message}")
    public SmileReturnObject unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        SmileReturnObject<String> returnObject = SmileReturnObject.success(message);
        returnObject.setCode("401");
        return returnObject;
    }
}
