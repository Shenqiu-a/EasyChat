package com.eastchat.easychatloginservice.common.Exception;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2811:45
 */

public class BizException extends RuntimeException {

    private final String code;

    public BizException(String message) {
        super(message);
        this.code = "500";
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}