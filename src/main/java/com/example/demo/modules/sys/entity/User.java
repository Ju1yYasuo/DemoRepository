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
 * 员工信息
 * </p>
 *
 * @author luox
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ToString(callSuper = true)
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（1为男，2为女）
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
    private Long department;

    /**
     * 岗位id
     */
    private Long position;

    /**
     * 角色id
     */
    private String role;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系人电话
     */
    private String emergencyContactPhone;

    /**
     * 脸图文件名
     */
    private String facePicture;

    /**
     * 状态（0未登录，1已登录，2锁定）
     */
    private Integer status;

    /**
     * 登录时间
     */
    private Date logInTime;


}
