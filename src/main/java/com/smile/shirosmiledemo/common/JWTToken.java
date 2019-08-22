package com.smile.shirosmiledemo.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 15:42
 * @description：
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
