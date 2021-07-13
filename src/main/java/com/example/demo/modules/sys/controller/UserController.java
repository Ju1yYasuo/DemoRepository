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
 * 人员信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取人员信息
     *
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<User>> }
     * @author luox
     * @date 2021-07-13
     */
    @PostMapping("/getUser")
    public ResponseEntity<List<User>> getUser(@RequestBody QueryEntity<User> queryEntity) {
        return userService.getUser(queryEntity);
    }

    /**
     * 获取人员信息，自由字段过滤
     *
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<User>> }
     * @throws Exception 异常
     * @author luox
     * @date 2021/07/13
     */
    @PostMapping("/getUserByCommon")
    public ResponseEntity<List<User>> getUserByCommon(@RequestBody QueryEntity<User> queryEntity) throws Exception{
        return userService.getUserByCommon(queryEntity);
    }

    /**
     * 保存人员信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-13
     */
    @PostMapping("/saveUser")
    @Transactional
    public ResponseEntity<Boolean> saveUser(@RequestBody List<User> list){
        return userService.saveUser(list);
    }

    /**
     * 更新人员信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-13
     */
    @PutMapping("/updateUser")
    @Transactional
    public ResponseEntity<Boolean> updateUser(@RequestBody List<User> list){
        return userService.updateUser(list);
    }

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-13
     */
    @DeleteMapping("/deleteUser")
    @Transactional
    public ResponseEntity<Boolean> deleteUser(@RequestBody List<Integer> idList){
        return userService.deleteUser(idList);
    }

}
