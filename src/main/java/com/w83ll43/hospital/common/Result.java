package com.w83ll43.hospital.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果类
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.data = object;
        return result;
    }

    /**
     * 成功
     * @param message
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(String message, T object) {
        Result<T> result = new Result<>();
        result.message = message;
        result.code = 200;
        result.data = object;
        return result;
    }

    /**
     * 成功
     * @param message
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.message = message;
        result.code = 200;
        return result;
    }

    /**
     * 失败
     * @param message
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 400;
        result.message = message;
        return result;
    }

    /**
     * 失败 指定状态码
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }
}
