package com.example.demo.modules.sys.controller;

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.User;
import com.example.demo.modules.sys.service.UserService;
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
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<User>> }
     * @author luox
     * @date 2021/07/09
     */
    @PostMapping("/getUser")
    public ResponseEntity<List<User>> getUser(@RequestBody QueryEntity<User> queryEntity) {
        return userService.getUser(queryEntity);
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
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021/07/12
     */
    @PostMapping("/saveUser")
    @Transactional
    public ResponseEntity<Boolean> saveUser(@RequestBody List<User> list){
        return userService.saveUser(list);
    }

    /**
     * 更新员工信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021/07/12
     */
    @PutMapping("/updateUser")
    @Transactional
    public ResponseEntity<Boolean> updateUser(@RequestBody List<User> list){
        return userService.updateUser(list);
    }

    /**
     * 删除员工信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021/07/12
     */
    @DeleteMapping("/deleteUser")
    @Transactional
    public ResponseEntity<Boolean> deleteUser(@RequestBody List<Long> idList){
        return userService.deleteUser(idList);
    }

}
