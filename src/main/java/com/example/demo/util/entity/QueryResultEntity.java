package com.example.demo.util.entity;

import lombok.Data;

/**
 * 查询结果基础实体类
 *
 * @author luox
 * @date 2021/8/25
 */
@Data
public class QueryResultEntity<T> {

    /**
     * 总计
     */
    private int total;

    /**
     * 数据
     */
    private T data;

}
