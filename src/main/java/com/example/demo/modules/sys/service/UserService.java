package com.example.demo.modules.sys.service;

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-08
 */
public interface UserService extends IService<User> {

    ResponseEntity<List<User>> getUser(QueryEntity<User> queryEntity);
    ResponseEntity<List<User>> getUser2(QueryEntity<User> queryEntity) throws Exception;

    List<User> getUserBySex(Integer sex);

    ResponseEntity<Boolean> saveUser(List<User> list);

    ResponseEntity<Boolean> updateUser(List<User> list);

    ResponseEntity<Boolean> deleteUser(List<Long> idList);
}
