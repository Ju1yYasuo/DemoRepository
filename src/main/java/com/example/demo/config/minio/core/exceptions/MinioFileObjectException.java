package com.example.demo.config.minio.core.exceptions;

/**
 * minio文件对象异常
 *
 * @author luox
 * @date 2022/05/23
 */
public class MinioFileObjectException extends MinioException{
    private static final int CODE = 10002;

    public MinioFileObjectException(String message) {
        super(CODE, message);
    }

    public MinioFileObjectException(int code, String message) {
        super(code, message);
    }

    public MinioFileObjectException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public MinioFileObjectException(Throwable cause) {
        super(CODE, "异常", cause);
    }
}
