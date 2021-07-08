package com.example.demo.modules.system.service;

import com.example.demo.modules.system.entity.User;
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

    List<User> getAllUser();

    List<User> getUserBySex(Integer sex);

    boolean saveUsers(List<User> list);

    boolean updateUser(User user);

    boolean deleteUser(Long id);
}
