package com.example.demo.config.minio.core.service.impl;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.config.minio.config.MinioOsProperties;
import com.example.demo.config.minio.core.service.BucketPolicyService;
import com.example.demo.config.minio.core.service.MinioAbstractService;

/**
 * 桶策略服务
 *
 * @author luox
 * @date 2022/05/23
 */
@Slf4j
public class BucketPolicyServiceImpl extends MinioAbstractService implements BucketPolicyService {
    public BucketPolicyServiceImpl(MinioOsProperties minioOsProperties, MinioClient minioClient) {
        super(minioOsProperties, minioClient);
    }
}
