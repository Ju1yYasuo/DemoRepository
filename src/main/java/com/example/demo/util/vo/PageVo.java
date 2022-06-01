package com.example.demo.util.vo;

import lombok.Data;

/**
 * 分页vo类
 *
 * @author luox
 * @date 2022/6/1
 */
@Data
public class PageVo<T> {

    /**
     * 页面num
     */
    private long pageNum;

    /**
     * 页面大小
     */
    private long pageSize;

    /**
     * 总计
     */
    private long total;

    /**
     * 数据
     */
    private T data;
}
