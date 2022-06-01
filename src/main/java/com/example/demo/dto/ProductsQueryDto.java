package com.example.demo.dto;

import com.example.demo.util.entity.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 产品es查询dto
 *
 * @author luox
 * @date 2022/5/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductsQueryDto extends QueryEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;
}
