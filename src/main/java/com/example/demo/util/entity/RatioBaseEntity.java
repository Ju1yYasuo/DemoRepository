package com.example.demo.util.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 比例基础entity
 *
 * @author luox
 * @date 2022/05/31
 */
@Data
public class RatioBaseEntity {

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 比率
     */
    private Long ratio = 0L;
}
