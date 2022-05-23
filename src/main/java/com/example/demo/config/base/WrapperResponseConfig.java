package com.example.demo.config.base;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Iterator;
import java.util.List;

/**
 * 包装器响应配置
 *
 * @author luox
 * @date 2022/05/23
 */
@Configuration
public class WrapperResponseConfig implements WebMvcConfigurer {
    @Bean
    public ResponseBodyWrapFactoryBean responseBodyWrapFactoryBean() {
        return new ResponseBodyWrapFactoryBean();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()){
            HttpMessageConverter<?> next = iterator.next();
            if (next instanceof FastJsonHttpMessageConverter) {
//                next.
            }
        }
    }
}
