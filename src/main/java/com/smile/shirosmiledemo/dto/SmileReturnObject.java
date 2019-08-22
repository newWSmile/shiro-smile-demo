package com.smile.shirosmiledemo.dto;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 14:04
 * @description：
 */
public class SmileReturnObject<T> {
    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的内容
     */
    private T data;


    /**
     * 成功时候的调用
     *
     * @param <T>
     * @return
     */
    public static <T> SmileReturnObject<T> success(T data) {
        return new SmileReturnObject<>(data);
    }


    /**
     * 根据返回的状态对象， 构建返回结果
     *
     * @param SmileReturnObjectEnum
     * @param <T>
     * @return
     */
    public static <T> SmileReturnObject<T> build(SmileResultEnum SmileReturnObjectEnum) {
        return new SmileReturnObject<>(SmileReturnObjectEnum);
    }

    /**
     * 根据 code， 和  msg 构建返回结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> SmileReturnObject<T> build(String code, String msg) {
        return new SmileReturnObject<T>(code, msg);
    }

    /**
     * 根据 code， 和  msg, 和 data 构建返回结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> SmileReturnObject<T> build(String code, String msg, T data) {
        return new SmileReturnObject<T>(code, msg, data);
    }

    /**
     * 失败的调用
     *
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> SmileReturnObject<T> error(String codeMsg) {
        return new SmileReturnObject<>(codeMsg);
    }

    /**
     * 失败的调用 将返回结果传入
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> SmileReturnObject<T> error(T data) {
        return new SmileReturnObject<>(SmileResultEnum.ERROR.getCode(), SmileResultEnum.ERROR.getMsg(), data);
    }

    private SmileReturnObject(T data) {
        this.code = SmileResultEnum.SUCCESS.getCode();
        this.msg = SmileResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private SmileReturnObject(String msg) {
        this.code = SmileResultEnum.ERROR.getCode();
        this.data = null;
        this.msg = msg;
    }

    private SmileReturnObject(SmileResultEnum SmileReturnObjectEnum) {
        this.code = SmileReturnObjectEnum.getCode();
        this.msg = SmileReturnObjectEnum.getMsg();
    }

    private SmileReturnObject(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private SmileReturnObject(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
