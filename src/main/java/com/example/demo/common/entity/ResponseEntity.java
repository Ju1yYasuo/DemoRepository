package com.example.demo.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author luox
 * @date 2021/7/9
 */
@Data
@AllArgsConstructor
public class ResponseEntity<T> {

    public static final String success = "success";
    public static final String fail = "fail";

    public static final int OK = 200;
    public static final int REQUEST_ERROR = 404;
    public static final int SERVER_ERROR = 500;
    public static final int UNAUTHORIZED = 401;
    public static final int DISCONTENT = 412;

    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

}
