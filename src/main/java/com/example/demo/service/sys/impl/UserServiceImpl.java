package com.example.demo.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.sys.User;
import com.example.demo.mapper.sys.UserMapper;
import com.example.demo.service.sys.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author luox
 * @since 2021-07-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(Page<User> page, String fuzzySearch){
        List<User> list;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if(page != null){
            list = page(page,queryWrapper).getRecords();
        }else{
            list = list(queryWrapper);
        }
        return list;
    }
    
    @Override
    public Boolean saveUser(User user) {
        return save(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return updateById(user);
    }

    @Override
    public Boolean deleteUser(List<Integer> idList) {
        return removeByIds(idList);
    }
    
}
