package com.example.demo.util.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页类
 *
 * @author luox
 * @date 2022/01/13
 */
@Data
public class BaseQueryVo<T> {

    /**
     * 每页显示条数
     */
    private long pageSize;

    /**
     * 当前页数
     */
    private long pageNum;

    /**
     * 关键字
     */
    private String searchKey;

    public BaseQueryVo(){
        this.pageSize = 10L;
        this.pageNum = 1L;
    }

    public Page<T> toPage(){
        return new Page<>(this.pageNum,this.pageSize);
    }
}
