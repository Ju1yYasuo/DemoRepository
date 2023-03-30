package com.example.demo.config.minio.core.service;

import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import com.example.demo.config.minio.core.exceptions.MinioBucketException;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * bucket 相关的操作
 *
 * @author luox
 * @date 2022/05/23
 */
public interface BucketService {

    /**
     * 是否存在 bucket
     *
     * @param bucketName 存储桶名称
     * @return true 表示存在，false 表示不存在
     * @throws MinioBucketException Bucket相关异常
     */
    boolean bucketExists(@NonNull String bucketName) throws MinioBucketException;

    boolean bucketExists() throws MinioBucketException;

    /**
     * 创建一个新的 Bucket
     *
     * @param bucketName 存储桶名称
     * @throws MinioBucketException Bucket相关异常
     */
    void makeBucket(@NonNull String bucketName) throws MinioBucketException;

    /**
     * 列出所有存储桶。
     *
     * @return List<Bucket> 桶集合
     * @throws MinioBucketException Bucket相关异常
     */
    List<Bucket> listBuckets() throws MinioBucketException;

    /**
     * 删除一个存储桶。
     *
     * @param bucketName 存储桶名称
     * @throws MinioBucketException Bucket相关异常
     */
    void removeBucket(String bucketName) throws MinioBucketException;

    /**
     * 列出某个存储桶中的所有对象。
     *
     * @param bucketName  存储桶名称
     * @param prefix      对象名称的前缀
     * @param recursive   是否递归查找，如果是false,就模拟文件夹结构查找。
     * @param useVersion1 如果是true, 使用版本1 REST API
     * @return Iterable<Result < Item>>
     */
    Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive, boolean useVersion1);

}
