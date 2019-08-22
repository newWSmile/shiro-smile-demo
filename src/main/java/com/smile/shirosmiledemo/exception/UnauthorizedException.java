package com.smile.shirosmiledemo.exception;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 15:41
 * @description：
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }


}
