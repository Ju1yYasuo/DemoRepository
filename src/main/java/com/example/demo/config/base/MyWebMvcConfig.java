package com.example.demo.config.base;

import cn.hutool.core.util.StrUtil;
import com.example.demo.config.annotation.InterceptorAnn;
import com.example.demo.config.interceptor.MyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * web配置
 *
 * @author luox
 * @date 2021/7/29
 */
@Configuration
@Slf4j
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //先添加核心拦截器 (通过继承来处理),继承CoreInterceptor
        String[] defaultExclude = new String[]{"/plumelog/**", "/res/**", "/static/**", "/localres/**", "/error", "/hmq/sys/**", "/favicon.ico"};
        //通过自定义来解决日志问题
        //registry.addInterceptor(new PlumeLogTraceIdInterceptor()).excludePathPatterns(defaultExclude);

        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForType(HandlerInterceptor.class);
        for (int i = 0; i < beanNamesForAnnotation.length; i++) {
            log.info(String.format("加载拦截器%s", beanNamesForAnnotation[i]));
            HandlerInterceptor interceptorClass = (HandlerInterceptor) applicationContext.getBean(beanNamesForAnnotation[i]);
            InterceptorAnn annotation = interceptorClass.getClass().getAnnotation(InterceptorAnn.class);

            InterceptorRegistration registrationAdd = registry.addInterceptor(interceptorClass);
            if (annotation == null) {
                registrationAdd.addPathPatterns("/**");
            } else {
                if (annotation.exclude() != null && annotation.exclude().length > 0) {
                    registrationAdd.excludePathPatterns(annotation.exclude());
                }
                if (annotation.path() != null && annotation.path().length > 0) {
                    //拦截资源用默认的
                    registrationAdd.addPathPatterns(annotation.path());
                }
            }
            //追加默认资源的排除
            registrationAdd.excludePathPatterns(defaultExclude);
        }

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
