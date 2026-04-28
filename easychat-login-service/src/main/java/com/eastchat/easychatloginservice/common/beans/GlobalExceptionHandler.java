package com.eastchat.easychatloginservice.common.beans;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2814:25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public PackedResult<?> handleBizException(BizException e) {
        return PackedResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public PackedResult<?> handleException(Exception e) {
        return PackedResult.error("500", "系统异常");
    }
}
