package com.example.demo.config.minio.core.service.impl;

import com.example.demo.util.vo.PutObjectRespVo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.config.minio.config.MinioOsProperties;
import com.example.demo.config.minio.core.exceptions.MinioFileObjectException;
import com.example.demo.config.minio.core.service.FileObjectService;
import com.example.demo.config.minio.core.service.MinioAbstractService;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件对象操作服务
 *
 * @author luox
 * @date 2022/05/23
 */
@Slf4j
public class FileObjectServiceImpl extends MinioAbstractService implements FileObjectService {

    public FileObjectServiceImpl(MinioOsProperties minioOsProperties, MinioClient minioClient) {
        super(minioOsProperties, minioClient);
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioFileObjectException(e);
        }
    }

    @Override
    public PutObjectRespVo putObject(String bucketName, InputStream inputStream, long objectSize, long partSize, String fileName, String contentType) {
        PutObjectArgs.Builder builder = PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(inputStream, objectSize, partSize);
        if (StringUtils.hasText(contentType)) {
            builder.contentType(contentType);
        }
        ObjectWriteResponse objectWriteResponse;
        try {
            objectWriteResponse = minioClient.putObject(builder.build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioFileObjectException(e);
        }
        StringBuffer url = new StringBuffer(minioOsProperties.getEndpoint()).append(":").append(minioOsProperties.getPort()).append("/").append(objectWriteResponse.bucket()).append("/").append(objectWriteResponse.object());
        return PutObjectRespVo.builder().bucket(bucketName).objectName(fileName).url(url.toString()).build();
    }

    @Override
    public PutObjectRespVo putObject(InputStream inputStream, long objectSize, long partSize, String fileName, String contentType) {
        return this.putObject(minioOsProperties.getBucket(), inputStream, objectSize, partSize, fileName, contentType);
    }

    @Override
    public void uploadObject(String bucketName, String objectName, String fileName) {
        try {
            minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioFileObjectException(e);
        }
    }

    @Override
    public void downloadObject(String bucketName, String objectName) throws MinioFileObjectException {
        downloadObject(bucketName, objectName, null);
    }

    @Override
    public void downloadObject(String bucketName, String objectName, String fileName) throws MinioFileObjectException {
        try {
            minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(objectName).filename(StringUtils.hasText(fileName) ? fileName : objectName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioFileObjectException(e);
        }
    }

    @Override
    public String getPresignedObjectUrl(String bucketName, String objectName) {
        GetPresignedObjectUrlArgs.Builder expiry = GetPresignedObjectUrlArgs.builder().bucket(bucketName).method(Method.GET).object(objectName).expiry(GetPresignedObjectUrlArgs.DEFAULT_EXPIRY_TIME);
        try {
            return minioClient.getPresignedObjectUrl(expiry.build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioFileObjectException(e);
        }
    }

    @Override
    public StatObjectResponse statObject(String bucketName, String objectName) {
        try {
            return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioFileObjectException(e);
        }
    }

    @Override
    public StatObjectResponse statObject(String objectName) {
        return this.statObject(minioOsProperties.getBucket(), objectName);
    }

    @Override
    public boolean existObject(String bucketName, String objectName) {
        return false;
    }
}
