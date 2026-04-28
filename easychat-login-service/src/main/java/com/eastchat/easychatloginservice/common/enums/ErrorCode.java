package com.eastchat.easychatloginservice.common.enums;

public enum ErrorCode {
    /**
     * 错误码
     */
    SUCCESS("1001", "成功"),
    USER_NOT_EXIST("1002", "用户不存在"),
    USER_EXIST("1003", "用户已存在"),
    USER_PASSWORD_ERROR("1004", "用户密码错误"),
    USER_MOBILE_ERROR("1005", "用户手机号错误"),
    USER_EMAIL_ERROR("1006", "用户邮箱错误"),
    USER_CODE_ERROR("1007", "用户验证码错误"),
    USER_CODE_EXPIRED("1008", "用户验证码已过期");

    private final String code;
    private final String msg;

    ErrorCode(String code, String msg) {
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
