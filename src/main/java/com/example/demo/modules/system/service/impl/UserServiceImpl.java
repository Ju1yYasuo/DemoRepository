package com.example.demo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.modules.system.entity.User;
import com.example.demo.modules.system.mapper.UserMapper;
import com.example.demo.modules.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author luox
 * @since 2021-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public List<User> getUserBySex(Integer sex) {
        return userMapper.selectList(new QueryWrapper<User>().eq("sex",sex));
//        QueryWrapper queryWrapper = new QueryWrapper<User>().eq("sex",sex);
//        return list(queryWrapper);
    }

    @Override
    public boolean saveUsers(List<User> list) {
        return saveBatch(list);
    }

    @Override
    public boolean updateUser(User user) {
        return updateById(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return removeById(id);
    }
}
