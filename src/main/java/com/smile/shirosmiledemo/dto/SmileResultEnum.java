package com.smile.shirosmiledemo.dto;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:07
 * @description：
 */
public enum  SmileResultEnum {

    SUCCESS("0000","成功"),

    ERROR("9999","失败"),

    WAIT("1111","正在处理结果");

    private String code;

    private String msg;

    SmileResultEnum(String code, String msg) {
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
