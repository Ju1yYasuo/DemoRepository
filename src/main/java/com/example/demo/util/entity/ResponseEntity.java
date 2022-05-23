package com.example.demo.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应实体类
 *
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
    public static final int ERROR = 300;
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

    public static <T> ResponseEntity<T> info(boolean b) {
        ResponseEntity<T> ret = new ResponseEntity<>();
        ret.setCode(b ? OK : ERROR);
        ret.setMessage(b ? "操作成功" : "操作失败");
        return ret;
    }

    public static <T> ResponseEntity<T> info(int b) {
        ResponseEntity<T> ret = new ResponseEntity<>();
        ret.setCode(b > 0 ? OK : ERROR);
        ret.setMessage(b > 0 ? "操作成功" : "操作失败");
        return ret;
    }

    public static <T> ResponseEntity<T> error() {
        return error(REQUEST_ERROR, FAIL, null);
    }

    public static <T> ResponseEntity<T> errorMessage(String msg) {
        return error(REQUEST_ERROR, msg, null);
    }

    public static <T> ResponseEntity<T> error(int code, String message, T data) {
        ResponseEntity<T> ret = new ResponseEntity<>();
        ret.setCode(code);
        ret.setMessage(message);
        ret.setData(data);
        return ret;
    }

}
