package com.eastchat.easychatloginservice.common.enums;

public enum APIErrorCode implements ErrorCodeInterface{
    /****
     * 无权限异常CODE:101XXX
     */
    UN_AUTH("101400", "无权限!"),
    USER_UN_LOGIN("101401", "未登录!"),
    USER_UN_AUTH("101405", "无权限!"),
    VALID_EXCEPTION("100001", "参数校验失败!"),
    API_UN_AUTH("101406", "接口调用无权限!"),
    UN_SAFE_EVENT("999999", "识别存在非安全事件!");


    private final String code;

    private final String msg;

    APIErrorCode(String code, String msg) {
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
