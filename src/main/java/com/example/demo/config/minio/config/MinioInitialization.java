package com.example.demo.config.minio.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 初始化minio服务
 *
 * @author luox
 * @date 2022/05/23
 */
//@Configuration(proxyBeanMethods = false)
//@EnableConfigurationProperties(MinioOsProperties.class)
//@Import({MinioConfiguration.class})
//@ConditionalOnProperty(prefix = "spring.minio", name = "endpoint", matchIfMissing = false)
public class MinioInitialization {
}
