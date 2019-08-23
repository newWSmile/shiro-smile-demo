package com.smile.shirosmiledemo.common;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 14:14
 * @description：
 */
public class LoginToken extends UsernamePasswordToken {

    private boolean auto;

    public LoginToken(String username, String password){
        super(username, password);
        this.auto = false;
    }

    public LoginToken(String username) {
        super(username,"");
        this.auto = true;
    }


    public LoginToken() {
        super();
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }
}
