package com.example.demo.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.util.entity.BaseEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
     * 岗位id
     */
    private Integer position;

    /**
     * 工种
     */
    private Integer workType;

    /**
     * 职业代码
     */
    private String jobCode;

    /**
     * 职业等级
     */
    private Integer jobLevel;

    /**
     * 安全帽等级
     */
    private Integer helmetType;

    /**
     * 绑定安全帽
     */
    private String boundHelmet;

    /**
     * 人员编号
     */
    private String userNumber;

    /**
     * 是否进入大屏id
     */
    private Integer whetherScreen;

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
     * 账号到期时间
     */
    private Date accountExpirationDate;

    /**
     * 登录状态（0未登录，1已登录，2锁定）
     */
    private Integer logStatus;

    /**
     * 上次登录时间
     */
    private Date logLastTime;

    /**
     * 登录错误次数
     */
    private Integer logErrorNumber;


}
