package com.example.demo.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 *
 * @author luox
 * @date 2021/07/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ControllerAnn {
    String permission() default "";

    boolean needLogin() default true;

    int repeat() default 0;

    String repeatMsg() default "";

    String repeatParameter() default "";

    boolean refPermission() default false;

    long auth() default 0L;

    boolean tx() default false;

    int timeout() default 60;
}
