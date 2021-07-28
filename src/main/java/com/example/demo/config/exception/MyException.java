package com.example.demo.config.exception;


import cn.hutool.core.util.StrUtil;

/**
 * 自定义运行时异常
 *
 * @author luox
 * @date 2021/7/28
 */
public class MyException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public MyException(Throwable e) {
        super(StrUtil.format("{}: {}", new Object[]{e.getClass().getSimpleName(), e.getMessage()}), e);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public MyException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MyException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
