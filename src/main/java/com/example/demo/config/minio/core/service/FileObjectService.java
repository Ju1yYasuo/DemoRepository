package com.example.demo.config.minio.core.service;


import io.minio.StatObjectResponse;
import com.example.demo.config.minio.core.exceptions.MinioFileObjectException;
import org.riie.admin.vo.PutObjectRespVo;

import java.io.InputStream;

/**
 * 文件对象操作服务
 *
 * @author luox
 * @date 2022/05/23
 */
public interface FileObjectService {

    /**
     * 获取对象流
     *
     * @param bucketName 桶名字
     * @param objectName 对象名字
     * @return {@link InputStream }
     * @author chenwz
     * @date 2022-05-11
     */
    InputStream getObject(String bucketName, String objectName);

    /**
     * 上传对象
     *
     * @param bucketName  桶名字
     * @param inputStream 输入流
     * @param objectSize  文件大小 不知道填 -1
     * @param partSize    分片长度 不知道填 -1
     * @param fileName    文件名称
     * @param contentType 文件类型
     * @return {@link String }
     * @author chenwz
     * @date 2022-05-11
     */
    PutObjectRespVo putObject(String bucketName, InputStream inputStream, long objectSize, long partSize, String fileName, String contentType);

    PutObjectRespVo putObject(InputStream inputStream, long objectSize, long partSize, String fileName, String contentType);

    /**
     * 上传文件
     *
     * @param bucketName 桶名字
     * @param objectName 对象名字
     * @param fileName   文件名：绝对路径
     */
    void uploadObject(String bucketName, String objectName, String fileName);

    /**
     * 下载对象
     *
     * @param bucketName 桶名字
     * @param objectName 对象名字
     * @throws MinioFileObjectException 存储对象相关异常
     */
    void downloadObject(String bucketName, String objectName) throws MinioFileObjectException;

    /**
     * 下载对象
     *
     * @param bucketName 桶名字
     * @param objectName 对象名字
     * @param fileName   文件名字
     * @throws MinioFileObjectException 存储对象相关异常
     */
    void downloadObject(String bucketName, String objectName, String fileName) throws MinioFileObjectException;

    /**
     * 获得临时访问对象url 默认7天
     *
     * @param bucketName 桶名字
     * @param objectName 对象名字
     * @return {@link String }
     * @author chenwz
     * @date 2022-05-11
     */
    String getPresignedObjectUrl(String bucketName, String objectName);

    /**
     * 获取对象的元数据。
     *
     * @param bucketName 桶名
     * @param objectName 对象名
     * @return {@link StatObjectResponse }
     * @author chenwz
     * @date 2022-05-12
     */
    StatObjectResponse statObject(String bucketName, String objectName);

    /**
     * 获取对象的元数据。
     *
     * @param objectName 对象名称
     * @return {@link StatObjectResponse }
     * @author chenwz
     * @date 2022-05-12
     */
    StatObjectResponse statObject(String objectName);

    boolean existObject(String bucketName, String objectName);
}
