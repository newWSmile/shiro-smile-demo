package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.dto.SmileReturnObject;
import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:13
 * @description：
 */
@RestControllerAdvice
public class ExceptionController {

    // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AccountException.class)
    public SmileReturnObject handleShiroException(Exception ex) {
        return SmileReturnObject.error(ex.getMessage());
    }


}
