package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.dto.SmileReturnObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:11
 * @description：
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @RequiresRoles(value = "admin,high")
    public SmileReturnObject getMessage() {
        return SmileReturnObject.success("您拥有用户权限，可以获得该接口的信息！");
    }

}
