package com.example.demo.config.minio.core.exceptions;

import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * minio异常
 *
 * @author luox
 * @date 2022/05/23
 */
public class MinioException extends RuntimeException {
    private int code;
    private String message;
    private final static String ERROR_RESPONSE_MSG = "Minio服务器无响应";
    private final static String INSUFFICIENT_DATA_MSG = "Minio数据不足异常";
    private final static String INTERNAL_EXCEPTION_MSG = "Minio内部错误";
    private final static String INVALID_KEY_MSG = "无效密钥异常";
    private final static String INVALID_RESPONSE_SMG = "Minio无效响应异常";
    private final static String IO_EXCEPTION_SMG = "连接Minio异常";
    private final static String NO_SUCH_ALGORITHM_SMG = "没有此类算法异常";
    private final static String SERVER_EXCEPTION_SMG = "Minio服务器相应异常";
    private final static String XML_PARSER_EXCEPTION_SMG = "解析返回的XML异常";

    public MinioException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MinioException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        if (cause instanceof ErrorResponseException) {
            this.message = ERROR_RESPONSE_MSG;
        } else if (cause instanceof InsufficientDataException) {
            this.message = INSUFFICIENT_DATA_MSG;
        } else if (cause instanceof InternalException) {
            this.message = INTERNAL_EXCEPTION_MSG;
        } else if (cause instanceof InvalidKeyException) {
            this.message = INVALID_KEY_MSG;
        } else if (cause instanceof InvalidResponseException) {
            this.message = INVALID_RESPONSE_SMG;
        } else if (cause instanceof IOException) {
            this.message = IO_EXCEPTION_SMG;
        } else if (cause instanceof NoSuchAlgorithmException) {
            this.message = NO_SUCH_ALGORITHM_SMG;
        } else if (cause instanceof ServerException) {
            this.message = SERVER_EXCEPTION_SMG;
        } else if (cause instanceof XmlParserException) {
            this.message = XML_PARSER_EXCEPTION_SMG;
        } else {
            this.message = cause.getMessage();
        }


    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
