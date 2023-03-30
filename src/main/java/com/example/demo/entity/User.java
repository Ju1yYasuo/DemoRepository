package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.example.demo.util.entity.FullBaseEntity;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author luox
 * @since 2021-07-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ToString(callSuper = true)
public class User extends FullBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    @TableField(fill = FieldFill.INSERT)
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（枚举型，1为男，2为女）
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 部门id
     */
    private Integer department;

    /**
     * 岗位key
     */
    private String position;

    /**
     * 工种key
     */
    private String workType;

    /**
     * 职业代码
     */
    private String jobCode;

    /**
     * 职业等级key
     */
    private String jobLevel;

    /**
     * 安全帽类型key
     */
    private String helmetType;

    /**
     * 绑定安全帽
     */
    private String boundHelmet;

    /**
     * 用户编号
     */
    private String userNumber;

    /**
     * 是否进入大屏key
     */
    private String whetherScreen;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系人电话
     */
    private String emergencyContactPhone;

    /**
     * 脸图文件标识
     */
    private String fileMark;

    /**
     * 账户有效期
     */
    @TableField(fill = FieldFill.INSERT)
    private Date accountExpirationDate;

    /**
     * 登录状态（0未登录，1已登录，2锁定）
     * @ignore
     */
    private Integer logStatus;

    /**
     * 上次登录时间
     * @ignore
     */
    private Date logLastTime;

    /**
     * 登录错误次数
     * @ignore
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer logErrorNumber;
}
