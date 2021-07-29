package com.example.demo.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author luox
 * @date 2021/7/29
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                ;
                //可以选择过滤路径，现根据请求controller时注解控制
                //.excludePathPatterns("/sys/user/login");

    }

}
