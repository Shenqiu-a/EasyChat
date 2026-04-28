package com.eastchat.easychatloginservice.common.enums;

/**
 * 错误码接口
 * 实现 PackedAssert 中错误码的泛用
 * TODO 研究下接口怎么实现的泛用
 */
public interface ErrorCodeInterface {
    String getCode();
    String getMsg();
}
