package com.example.demo.util.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 基础实体类
 *
 * @author luox
 * @date 2021/7/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FullBaseEntity extends IdEntity{

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
    private String remark;

    /**
     * 创建人
     * @ignore
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 更新人
     * @ignore
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
