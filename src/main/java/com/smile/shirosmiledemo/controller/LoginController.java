package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.dto.SmileReturnObject;
import com.smile.shirosmiledemo.dto.UserDto;
import com.smile.shirosmiledemo.entity.User;
import com.smile.shirosmiledemo.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public SmileReturnObject login(@RequestBody UserDto userDto) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUserName(), userDto.getUserPassword());
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        List<User> userList = userRepository.findByUserName(userDto.getUserName());
        if (null == userList || userList.size() == 0) {
            throw new AccountException("用户名不正确");
        }
        User user = userList.get(0);
        String role = user.getUserType();
        if ("user".equals(role)) {
            return SmileReturnObject.success("欢迎登陆");
        }
        if ("admin".equals(role)) {
            return SmileReturnObject.success("欢迎来到管理员页面");
        }
        return SmileReturnObject.error("权限错误！");
    }
}
