package com.eastchat.easychatloginservice.common.enums;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/309:51
 */

public enum RegisterErrorCode implements ErrorCodeInterface{
    /***
     * 用户注册异常 CODE:102XXX
     */
    USER_IS_EXIST("102001", "用户已注册"),
    USER_IS_ILLEGAL("102002", "用户信息不合法"),
    USER_REGISTER_FAIL("102003", "用户注册失败"),
    USER_REGISTER_CODE_ERROR("102004", "用户注册验证码错误"),
    USER_REGISTER_CODE_EXPIRED("102005", "用户注册验证码已过期"),
    USER_PASSWORD_IS_NULL("102006","用户密码不能为空"),
    USER_ACCOUNT_IS_NULL("102007","用户手机号/邮箱不能为空");

    private final String code;
    private final String msg;

    RegisterErrorCode(String code, String msg) {
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
