package com.example.demo.config.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import com.example.demo.config.annotation.InterceptorAnn;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 注解权限拦截器
 *
 * @author luox
 * @date 2022/05/23
 */
//@Component
@InterceptorAnn(path = "/**",exclude = {"/sys/login"})
public class SaTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            StpUtil.checkLogin();
            Method method = ((HandlerMethod) handler).getMethod();
            SaStrategy.me.checkMethodAnnotation.accept(method);
        }
        return true;
    }
}
