package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 费用类
 *
 * @author luox
 * @date 2022/6/24
 */
@Data
@Builder
public class Cost {

    private Integer type;

    private Integer timeType;

    private Integer orgId;

    private String totalMonth;

    private BigDecimal cost;
}
