package com.example.demo.config.minio.core.exceptions;

/**
 * 桶相关异常
 *
 * @author luox
 * @date 2022/05/23
 */
public class MinioBucketException extends MinioException {

    private static final int CODE = 10001;

    public MinioBucketException(String message) {
        super(CODE, message);
    }

    public MinioBucketException(int code, String message) {
        super(code, message);
    }

    public MinioBucketException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public MinioBucketException(Throwable cause) {
        super(CODE, "异常", cause);
    }

}
