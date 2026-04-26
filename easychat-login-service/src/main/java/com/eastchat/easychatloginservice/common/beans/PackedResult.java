package com.eastchat.easychatloginservice.common.beans;

import lombok.Data;

@Data
public class PackedResult <T> {

    // 状态码
    private Integer code;

    // 提示信息
    private String message;

    // 数据
    private T data;

    // 私有化的构造方法
    private PackedResult () {}

    // 成功的方法
    public static <T> PackedResult<T> success(){
        return success(null);
    }

    public static <T> PackedResult<T> success(T data) {
        PackedResult<T> result = new PackedResult<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return  result;
    }

    public static <T> PackedResult<T> success(String msg, T data){
        PackedResult<T> result = new PackedResult<T>();
        result.setCode(200);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    // 失败的方法
    public static <T> PackedResult<T> error() {
        return error(500,null);
    }

    public static <T> PackedResult<T> error (Integer code,String msg) {
        PackedResult<T> result = new PackedResult<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}

