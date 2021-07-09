package com.example.demo.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.QueryVoEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.User;
import com.example.demo.modules.sys.mapper.UserMapper;
import com.example.demo.modules.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    public ResponseEntity<List<User>> getAllUser(QueryVoEntity<User> queryVoEntity) {
        Page<User> page = new Page<>();
        page.setCurrent(queryVoEntity.getCurrent());
        page.setSize(queryVoEntity.getSize());
        QueryWrapper queryWrapper = new QueryWrapper();
//        QueryWrapper queryWrapper2 = Wrappers.query();

        Class<User> userClass = User.class;
        Field[] fields = userClass.getFields();



        User user = queryVoEntity.getEntity();

        return new ResponseEntity<>(ResponseEntity.OK,"success",userMapper.selectPage(page,null).getRecords());
//        return userMapper.selectList(null);

    }

    @Override
    public List<User> getUserBySex(Integer sex) {
//        return userMapper.selectList(new QueryWrapper<User>().eq("sex",sex));
//        QueryWrapper queryWrapper = new QueryWrapper<User>().eq("sex",sex);
//        return list(queryWrapper);
        return userMapper.getUserBySex(sex);
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
