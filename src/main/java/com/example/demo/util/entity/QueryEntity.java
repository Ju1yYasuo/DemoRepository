package com.example.demo.util.entity;

import lombok.Data;

/**
 * 查询实体类
 * 利于扩展查询字段
 * @author luox
 * @date 2021/7/9
 */
@Data
public class QueryEntity {

    /**
     * 当前页码
     */
    private int current = 1;

    /**
     * 分页大小
     */
    private int size = 10;
}
