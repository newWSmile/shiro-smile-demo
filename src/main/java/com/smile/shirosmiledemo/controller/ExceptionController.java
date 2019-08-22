package com.smile.shirosmiledemo.controller;

import com.smile.shirosmiledemo.dto.SmileReturnObject;
import com.smile.shirosmiledemo.exception.UnauthorizedException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public SmileReturnObject handle401(ShiroException e) {
        return SmileReturnObject.error(e.getMessage());
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public SmileReturnObject handle401() {
        return SmileReturnObject.error("Unauthorized");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SmileReturnObject globalException(HttpServletRequest request, Throwable ex) {
        return SmileReturnObject.error(ex.getMessage());
    }
}
