package com.eastchat.easychatloginservice.common.enums;

public enum LoginErrorCode implements ErrorCodeInterface{
    /***
     * 用户异常CODE:102XXX
     */
    USER_NOT_EXIST("1002", "用户不存在"),
    USER_EXIST("1003", "用户已存在"),
    USER_ACCOUNT_IS_NULL("1004", "用户账号为空"),
    USER_PASSWORD_IS_NULL("1005", "用户密码为空"),
    USER_PASSWORD_ERROR("1006", "用户密码错误"),
    USER_MOBILE_ERROR("1007", "用户手机号不合法"),
    USER_EMAIL_ERROR("1008", "用户邮箱不合法"),
    USER_CODE_IS_NULL("1009", "用户验证码为空"),
    USER_CODE_ERROR("1010", "用户验证码错误"),
    USER_CODE_EXPIRED("1011", "用户验证码已过期");

    private final String code;

    private final String msg;

    LoginErrorCode(String code, String msg) {
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
