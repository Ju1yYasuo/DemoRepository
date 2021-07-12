package com.example.demo.common.entity;

import lombok.Data;

/**
 * @author luox
 * @date 2021/7/9
 */
@Data
public class QueryEntity<T> {

    /**
     * 当前页码
     */
    private Long current;

    /**
     * 分页大小
     */
    private Long size;

    /**
     * 查询实体
     */
    private T entity;

}
