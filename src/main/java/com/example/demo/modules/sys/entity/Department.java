package com.example.demo.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.example.demo.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

/**
 * <p>
 * 部门信息
 * </p>
 *
 * @author luox
 * @since 2021-07-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_department")
@ToString(callSuper = true)
public class Department extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门id
     */
    private Long superDepartment;

    /**
     * 部门编码
     */
    private String departmentCode;

    /**
     * 更新时间
     */
    private Date updateDate;


}
