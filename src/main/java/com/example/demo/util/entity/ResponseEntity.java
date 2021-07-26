package com.example.demo.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luox
 * @date 2021/7/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

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

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> ResponseEntity<T> success() {
        ResponseEntity<T> ret = new ResponseEntity<>();
        ret.setCode(OK);
        ret.setMessage(SUCCESS);
        return ret;
    }

}
