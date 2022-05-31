package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;

/**
 * 用户登录结果dto
 *
 * @author luox
 * @date 2021/9/2
 */
@Data
public class UserLoginResultDto {

    /**
     * 用户信息
     */
    private User user;
    //
    ///**
    // * 角色
    // */
    //private List<Role> roles;
    //
    ///**
    // * 菜单权限
    // */
    //private List<Permissions> menuPermissions;
    //
    ///**
    // * 按钮权限
    // */
    //private List<Permissions> buttonPermissions;

    /**
     * 是否第一次登录
     */
    private Boolean whetherFirstLog;

    /**
     * token令牌
     */
    private String gkptToken;
}
