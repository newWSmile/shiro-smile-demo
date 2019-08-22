package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.dto.SmileReturnObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 11:07
 * @description：
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public SmileReturnObject login() {
        return SmileReturnObject.success("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public SmileReturnObject submitLogin() {
        return SmileReturnObject.success("您拥有获得该接口的信息的权限");
    }
}