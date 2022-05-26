package com.example.demo.dto.sys;

import lombok.Data;

/**
 * 用户登录dto
 *
 * @author luox
 * @date 2021/8/25
 */
@Data
public class UserLoginDto {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
