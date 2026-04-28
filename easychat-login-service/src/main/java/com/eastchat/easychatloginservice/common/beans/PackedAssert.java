package com.eastchat.easychatloginservice.common.beans;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import com.eastchat.easychatloginservice.common.enums.ErrorCode;
import java.util.*;

/**
 * 功能：自己封装的断言工具类，支持自定义错误码
 * 作者：yml
 * 日期：2026/4/2811:33
 */

public class PackedAssert {
    private PackedAssert () {}

    /**
     * 基础校验
     */
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new BizException(message);
        }
    }

    public static void isTrue(boolean condition, ErrorCode errorCode) {
        if (!condition) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new BizException(message);
        }
    }

    public static void isFalse(boolean condition, ErrorCode errorCode) {
        if (condition) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    /**
     * 对象校验
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BizException(message);
        }
    }

    public static void isNull(Object object, ErrorCode errorCode) {
        if (object != null) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object == null) {
            throw new BizException(message);
        }
    }
    public static void isNotNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    /**
     * 字符串校验
     */
    public static void isBlank(String str, String message) {
        if (str != null && !str.isEmpty()) {
            throw new BizException(message);
        }
    }

    public static void isBlank(String str, ErrorCode errorCode) {
        if (str != null && !str.isEmpty()) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    public static void isNotBlank(String str, String message) {
        if (str == null || str.isEmpty()) {
            throw new BizException(message);
        }
    }

    public static void isNotBlank(String str, ErrorCode errorCode) {
        if (str == null || str.isEmpty()) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    /**
     * 集合校验
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(message);
        }
    }

    public static void notEmpty(Collection<?> collection,ErrorCode errorCode) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    public static void notEmpty(Map<?,?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new BizException(message);
        }
    }

    public static void notEmpty(Map<?,?> map, ErrorCode errorCode) {
        if (map == null || map.isEmpty()) {
            throw new BizException(errorCode.getCode(), errorCode.getMsg());
        }
    }

    /**
     * 数值校验
     */
    public static void greaterThanZero(Number number,String message) {
        if (number == null || number.doubleValue() <= 0) {
            throw new BizException(message);
        }
    }

    public static void notNegative(Number number, String message) {
        if (number == null || number.doubleValue() < 0) {
            throw new BizException(message);
        }
    }

    /**
     * 通用失败
     */
    public static void fail(String message) {
        throw new BizException(message);
    }

    public static void fail(ErrorCode errorCode) {
        throw new BizException(errorCode.getCode(), errorCode.getMsg());
    }

}
