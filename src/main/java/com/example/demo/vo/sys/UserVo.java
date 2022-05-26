package com.example.demo.vo.sys;

import com.example.demo.entity.sys.User;
import lombok.*;

/**
 * 用户信息扩展类
 *
 * @author luox
 * @date 2021/8/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVo extends User {

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 工种名称
     */
    private String workTypeName;

    /**
     * 职业等级名称
     */
    private String jobLevelName;

    /**
     * 安全帽类型名称
     */
    private String helmetTypeName;

    /**
     * 是否进入大屏名称
     */
    private String whetherScreenName;

}
