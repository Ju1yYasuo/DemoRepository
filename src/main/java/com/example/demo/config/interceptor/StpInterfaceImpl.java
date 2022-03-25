package com.example.demo.config.interceptor;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.util.common.Constant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限验证接口
 *
 * @author luox
 * @date 2022/1/20
 */
//@Component
public class StpInterfaceImpl {
//public class StpInterfaceImpl implements StpInterface {

    ///**
    // * 返回一个账号所拥有的权限码集合,拥有admin角色返回所有权限
    // *
    // * @param loginId   登录id
    // * @param loginType 登录类型
    // * @return {@link List<String> }
    // * @author luox
    // * @date 2022/01/21
    // */
    //@Override
    //@Cacheable(value = Constant.USER_PERMISSION_CACHE,key = "#loginId")
    //public List<String> getPermissionList(Object loginId, String loginType) {
    //    SysRoleMapper sysRoleMapper = BeanUtil.getBean(SysRoleMapper.class);
    //    List<String> roleCodeList = sysRoleMapper.getUserRoleCode(Long.valueOf(loginId.toString()));
    //    SysResourceMapper sysResourceMapper = BeanUtil.getBean(SysResourceMapper.class);
    //    if(roleCodeList.contains(Constant.USER_ADMIN_ROLE)){
    //        return sysResourceMapper.selectObjs(Wrappers.lambdaQuery(SysResource.class).select(SysResource::getCode).eq(SysResource::getStatus,1))
    //                .stream().map(Object::toString).collect(Collectors.toList());
    //    }
    //
    //    return sysResourceMapper.getUserResourceCode(Long.valueOf(loginId.toString()));
    //}
    //
    ///**
    // * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
    // *
    // * @param loginId   登录id
    // * @param loginType 登录类型
    // * @return {@link List<String> }
    // * @author luox
    // * @date 2022/01/21
    // */
    //@Override
    //@Cacheable(value = Constant.USER_ROLE_CACHE,key = "#loginId")
    //public List<String> getRoleList(Object loginId, String loginType) {
    //    SysRoleMapper sysRoleMapper = BeanUtil.getBean(SysRoleMapper.class);
    //    return sysRoleMapper.getUserRoleCode(Long.valueOf(loginId.toString()));
    //}

}
