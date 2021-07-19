package com.example.demo.util.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author luox
 * @date 2021/7/8
 */
@Data
public class BaseEntity {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 逻辑删除（0 未删除、1 已删除）
     * @ignore
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     * @ignore
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     * @ignore
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
