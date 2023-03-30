package com.example.demo.config.minio.core.service;

import io.minio.MinioClient;
import com.example.demo.config.minio.config.MinioOsProperties;

/**
 * 抽象接口服务
 *
 * @author luox
 * @date 2022/05/23
 */
public abstract class MinioAbstractService implements MinioService {

    protected final MinioOsProperties minioOsProperties;
    protected final MinioClient minioClient;

    public MinioAbstractService(final MinioOsProperties minioOsProperties, final MinioClient minioClient) {
        this.minioOsProperties = minioOsProperties;
        this.minioClient = minioClient;
    }

    @Override
    public MinioOsProperties minioConfig() {
        return minioOsProperties;
    }

}
