package com.eastchat.easychatloginservice.common.beans;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能：全局异常处理
 * 作者：yml
 * 日期：2026/4/2814:25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public PackedResult<?> handleBizException(BizException e) {
        log.error("\n 业务异常：\n 异常编码:{} \n 异常信息: {}", e.getCode(), e.getMessage());
        return PackedResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public PackedResult<?> handleException(Exception e) {
        log.error("\n 系统异常：\n 错误信息: {}", e.getMessage());
        return PackedResult.error("500", "系统异常");
    }
}
