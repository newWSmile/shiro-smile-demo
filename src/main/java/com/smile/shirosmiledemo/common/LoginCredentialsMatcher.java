package com.smile.shirosmiledemo.common;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 14:16
 * @description：
 */
public class LoginCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        LoginToken loginToken = (LoginToken) token;
        if (loginToken.isAuto()){
            return true;
        }
        return super.doCredentialsMatch(token, info);
    }
}
