package com.example.demo.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luox
 * @date 2021/6/24
 */
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
//        e.printStackTrace();
        return "GlobalDefaultExceptionHandler : " + e.getMessage();
    }

}