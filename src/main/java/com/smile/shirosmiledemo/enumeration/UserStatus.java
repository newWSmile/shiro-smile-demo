package com.smile.shirosmiledemo.enumeration;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 9:36
 * @description：
 */
public enum UserStatus {

    EFFECTIVE("effective", "有效"),

    DISABLE("disable", "停用");

    private String code;

    private String msg;

    UserStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
