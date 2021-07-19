package com.example.demo.controller.sys;

import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.sys.User;
import com.example.demo.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 人员信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-16
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
     * @date 2021-07-16
     */
    @PostMapping("/getUser")
    public ResponseEntity<List<User>> getUser(@RequestBody QueryEntity<User> queryEntity) {
        return userService.getUser(queryEntity);
    }

    /**
     * 保存人员信息
     *
     * @param user 人员信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-16
     */
    @PostMapping("/saveUser")
    public ResponseEntity<Boolean> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    /**
     * 更新人员信息
     *
     * @param user 人员信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-16
     */
    @PostMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-16
     */
    @PostMapping("/deleteUser")
    public ResponseEntity<Boolean> deleteUser(@RequestBody List<Integer> idList){
        return userService.deleteUser(idList);
    }

}
