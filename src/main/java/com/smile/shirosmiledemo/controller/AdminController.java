package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.dto.SmileReturnObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:12
 * @description：
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public SmileReturnObject getMessage() {
        return SmileReturnObject.success("您拥有管理员权限，可以获得该接口的信息！");
    }
}
