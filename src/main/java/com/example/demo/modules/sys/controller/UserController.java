package com.example.demo.modules.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.QueryVoEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.User;
import com.example.demo.modules.sys.service.UserService;
import com.example.demo.modules.sys.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-08
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有员工信息
     *
     * @param queryVoEntity 查询签证官的实体
     * @return {@link ResponseEntity<List<User>> }
     * @author luox
     * @date 2021/07/09
     */
    @PostMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser(@RequestBody QueryVoEntity<User> queryVoEntity){
        return userService.getAllUser(queryVoEntity);
    }

    /**
     * 通过性别获取员工信息
     *
     * @param sex 性别
     * @return {@link String }
     * @author luox
     * @date 2021/07/08
     */
    @GetMapping("/getUserBySex")
    public String getUserBySex(@RequestParam("sex") Integer sex){
        return userService.getUserBySex(sex).toString();
    }

    /**
     * 保存员工信息
     *
     * @param list 列表
     * @return boolean
     * @author luox
     * @date 2021/07/08
     */
    @PostMapping("/saveUser")
    @Transactional
    public boolean saveUser(@RequestBody List<User> list){
        return userService.saveUsers(list);
    }

    /**
     * 更新员工信息
     *
     * @param user 员工信息
     * @return boolean
     * @author luox
     * @date 2021/07/08
     */
    @PutMapping("/updateUser")
    @Transactional
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 删除员工信息
     *
     * @param id id
     * @return boolean
     * @author luox
     * @date 2021/07/08
     */
    @DeleteMapping("/deleteUser")
    @Transactional
    public boolean deleteUser(@RequestParam("id") Long id){
        return userService.deleteUser(id);
    }

}
