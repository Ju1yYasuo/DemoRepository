package com.example.demo.util.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 一般查询entity
 *
 * @author luox
 * @date 2021/8/25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommonQueryEntity extends QueryEntity {

    /**
     * 模糊搜索
     */
    private String fuzzySearch;

}
