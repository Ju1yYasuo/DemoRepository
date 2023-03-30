package com.example.demo.util.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 基础实体类
 *
 * @author luox
 * @date 2021/7/8
 */
@Data
public class IdEntity {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
}
