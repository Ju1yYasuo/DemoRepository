package com.example.demo.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.sys.User;
import com.example.demo.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人员信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-26
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取人员信息
     *
     * @param map map
     * @return {@link ResponseEntity<List<User>> }
     * @author luox
     * @date 2021/07/26
     */
    @PostMapping("/getUser")
    public ResponseEntity<List<User>> getUser(@RequestBody Map<String,Object> map) {
            Page<User> page = null;
            String fuzzySearch = null;
            if(map.containsKey("current") && map.containsKey("size")){
                page = new Page<>();
                page.setCurrent(Long.parseLong(String.valueOf(map.get("current"))));
                page.setSize(Long.parseLong(String.valueOf(map.get("size"))));
            }
            if(map.containsKey("fuzzySearch")){
                fuzzySearch = (String) map.get("fuzzySearch");
            }
            return ResponseEntity.success(userService.getUser(page,fuzzySearch));
        }

    /**
     * 保存人员信息
     *
     * @param user 人员信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-26
     */
    @PostMapping("/saveUser")
    public ResponseEntity<Boolean> saveUser(@RequestBody User user){
        return ResponseEntity.success(userService.saveUser(user));
    }

    /**
     * 更新人员信息
     *
     * @param user 人员信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-26
     */
    @PostMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestBody User user){
        return ResponseEntity.success(userService.updateUser(user));
    }

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-26
     */
    @PostMapping("/deleteUser")
    public ResponseEntity<Boolean> deleteUser(@RequestBody List<Integer> idList){
        return ResponseEntity.success(userService.deleteUser(idList));
    }

}
