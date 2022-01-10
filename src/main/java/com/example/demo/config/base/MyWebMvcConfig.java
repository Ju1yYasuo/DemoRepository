package com.example.demo.config.base;

import com.example.demo.config.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
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
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
                //可以选择过滤路径，现根据请求controller时注解控制
                //.excludePathPatterns("/sys/user/login");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") //新版本版本
//                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

}
