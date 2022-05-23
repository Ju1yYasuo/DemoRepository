package com.example.demo.config.minio.config;

import com.example.demo.config.minio.core.service.BucketPolicyService;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.config.minio.core.service.BucketService;
import com.example.demo.config.minio.core.service.FileObjectService;
import com.example.demo.config.minio.core.service.impl.BucketPolicyServiceImpl;
import com.example.demo.config.minio.core.service.impl.BucketServiceImpl;
import com.example.demo.config.minio.core.service.impl.FileObjectServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * minio配置
 *
 * @author luox
 * @date 2022/05/23
 */
@Slf4j
public class MinioConfiguration {

    private final MinioOsProperties minioOsProperties;

    public MinioConfiguration(MinioOsProperties minioOsProperties) {
        log.info("minioConfiguration enable ");
        this.minioOsProperties = minioOsProperties;
        log.info("minio server start success");
    }


    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(minioOsProperties.getEndpoint(),
                        minioOsProperties.getPort(),
                        minioOsProperties.getSecure())
                .credentials(minioOsProperties.getAccessKey(),
                        minioOsProperties.getSecretKey())
                .region("cn-chengdu")
                .build();
    }

    @Bean
    public BucketPolicyService bucketPolicyService(MinioClient minioClient) {
        return new BucketPolicyServiceImpl(minioOsProperties, minioClient);
    }

    @Bean
    public FileObjectService fileObjectService(MinioClient minioClient) {
        return new FileObjectServiceImpl(minioOsProperties, minioClient);
    }

    @Bean
    public BucketService bucketService(MinioClient minioClient) {
        return new BucketServiceImpl(minioOsProperties, minioClient);
    }

}
