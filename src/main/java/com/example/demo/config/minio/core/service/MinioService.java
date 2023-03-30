package com.example.demo.config.minio.core.service;

import com.example.demo.config.minio.config.MinioOsProperties;

/**
 * 代表minio 服务
 *
 * @author luox
 * @date 2022/05/23
 */
public interface MinioService {
    MinioOsProperties minioConfig();
}
