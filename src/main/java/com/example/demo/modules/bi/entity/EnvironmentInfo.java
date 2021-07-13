package com.example.demo.modules.bi.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

/**
 * <p>
 * 环境信息
 * </p>
 *
 * @author luox
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bi_environment_info")
@ToString(callSuper = true)
public class EnvironmentInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * PM2.5(ug)
     */
    private BigDecimal pmTwoFive;

    /**
     * PM10(ug)
     */
    private BigDecimal pmTen;

    /**
     * 风速(level)
     */
    private BigDecimal windSpeed;

    /**
     * 噪音(dB)
     */
    private BigDecimal noise;

    /**
     * 湿度(%rh)
     */
    private BigDecimal humidity;

    /**
     * 温度(℃)
     */
    private BigDecimal temperature;

    /**
     * 渣土防护率(%)
     */
    private BigDecimal muckProtectionRate;

    /**
     * 表土保护率(%)
     */
    private BigDecimal topsoilProtectionRate;

    /**
     * 环境整改率(%)
     */
    private BigDecimal environmentalRectificationRate;


}
