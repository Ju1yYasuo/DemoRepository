package com.example.demo.config.minio.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Minio服务器配置
 *
 * @author luox
 * @date 2022/05/23
 */
@ConfigurationProperties(prefix = "spring.minio")
@Getter
@Setter
public class MinioOsProperties {

    /**
     * endPoint是一个URL，域名，IPv4或者IPv6地址。以下是合法的endpoints.
     */
    private String endpoint;
    /**
     * accessKey类似于用户ID，用于唯一标识你的账户.
     */
    private String accessKey;
    /**
     * 端口
     */
    private int port = 9000;
    /**
     * secretKey是你账户的密码
     */
    @JsonIgnoreProperties
    private String secretKey;
    /**
     * 如果是true，则用的是https而不是http,默认值是true。
     */
    private Boolean secure = false;

    private String bucket;

}
