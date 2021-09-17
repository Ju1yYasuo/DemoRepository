package com.example.demo.util.common;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * bean工具类
 *
 * @author luox
 * @date 2021/9/17
 */
public class BeanUtil {
    private static Map<String, Object> beanMap = new HashMap();
    private static ApplicationContext ctx;

    public BeanUtil() {
    }

    public static <T> T getBean(String name) {
        Object o = beanMap.get(name);
        if (o != null) {
            return (T) o;
        } else {
            T bean = (T) ctx.getBean(name);
            beanMap.put(name, bean);
            return bean;
        }
    }

    public static <T> T getBean(Class<T> requiredType) {
        Object o = beanMap.get(requiredType.getName());
        if (o != null) {
            return (T) o;
        } else {
            T bean = ctx.getBean(requiredType);
            beanMap.put(requiredType.getName(), bean);
            return bean;
        }
    }

    //public static RedisKit redis() {
    //    return (RedisKit)getBean(RedisKit.class);
    //}

    public static ApplicationContext getCtx() {
        return ctx;
    }

    public static void setCtx(ApplicationContext ctx) {
        BeanUtil.ctx = ctx;
    }
}