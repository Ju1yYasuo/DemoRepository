package com.example.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.util.entity.IdEntity;
import java.util.Date;
import lombok.*;
import java.io.Serializable;

/**
 * es-产品信息
 *
 * @author luox
 * @since 2022-05-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("bi_products")
@ToString(callSuper = true)
public class BiProducts extends IdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String description;
}
