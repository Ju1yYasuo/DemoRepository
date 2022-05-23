package com.example.demo.config.minio.core.service.impl;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import com.example.demo.config.minio.config.MinioOsProperties;
import com.example.demo.config.minio.core.exceptions.MinioBucketException;
import com.example.demo.config.minio.core.service.BucketService;
import com.example.demo.config.minio.core.service.MinioAbstractService;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * bucket 相关的操作
 *
 * @author luox
 * @date 2022/05/23
 */
@Slf4j
public class BucketServiceImpl extends MinioAbstractService implements BucketService {

    public BucketServiceImpl(MinioOsProperties minioOsProperties, MinioClient minioClient) {
        super(minioOsProperties, minioClient);
    }

    @Override
    public boolean bucketExists(@NotNull String bucketName) throws MinioBucketException {
        boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException |
                IOException | NoSuchAlgorithmException | InvalidKeyException |
                InvalidResponseException | XmlParserException | InternalException e) {
            throw new MinioBucketException(e);
        }
        return found;
    }

    @Override
    public boolean bucketExists() throws MinioBucketException {
        return false;
    }

    @Override
    public void makeBucket(@NonNull String bucketName) throws MinioBucketException {
        if (!this.bucketExists(bucketName)) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName).build());
            } catch (ErrorResponseException | InsufficientDataException | InternalException |
                    InvalidKeyException | InvalidResponseException | IOException |
                    NoSuchAlgorithmException | ServerException | XmlParserException e) {
                throw new MinioBucketException(e);
            }
        }
    }

    @Override
    public List<Bucket> listBuckets() throws MinioBucketException {
        try {
            return minioClient.listBuckets();
        } catch (ErrorResponseException | InsufficientDataException | InternalException |
                InvalidKeyException | InvalidResponseException | IOException |
                NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioBucketException(e);
        }
    }

    @Override
    public void removeBucket(String bucketName) throws MinioBucketException {
        if (this.bucketExists(bucketName)) {
            try {
                minioClient.removeBucket(RemoveBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            } catch (ErrorResponseException | InsufficientDataException | InternalException |
                    InvalidKeyException | InvalidResponseException | IOException |
                    NoSuchAlgorithmException | ServerException | XmlParserException e) {
                throw new MinioBucketException(e);
            }
        }
    }

    @Override
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive, boolean useVersion1) {
        if (this.bucketExists(bucketName)) {
            ListObjectsArgs.Builder bucket = ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(recursive)
                    .useApiVersion1(useVersion1);
            if (StringUtils.hasText(prefix)) {
                bucket.prefix(prefix);
            }
            return minioClient.listObjects(bucket.build());
        }
        return null;
    }

}
