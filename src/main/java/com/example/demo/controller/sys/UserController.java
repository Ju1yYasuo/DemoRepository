package com.example.demo.controller.sys;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.annotation.ControllerAnn;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.sys.User;
import com.example.demo.service.sys.UserService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
     * @return {@link ResponseEntity<Map<String,Object>> }
     * @author luox
     * @date 2021/07/26
     */
    @PostMapping("/getUser")
    public ResponseEntity<Map<String,Object>> getUser(@RequestBody Map<String,Object> map) {
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

    /**
     * 登录
     *
     * @param loginMap 登录Map
     * @return {@link ResponseEntity<User> }
     * @author luox
     * @date 2021/07/14
     */
    @PostMapping("/login")
    @ControllerAnn(needLogin = false)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginMap) {
        return ResponseEntity.success(userService.login(loginMap.get("userName"),loginMap.get("password")));
    }

    /**
     * 注销
     *
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021/07/28
     */
    @GetMapping("/logOut")
    public ResponseEntity<Boolean> logOut() {
        return ResponseEntity.success(userService.logOut());
    }

    /**
     * 更改密码
     *
     * @param map map
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021/07/23
     */
    @PostMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody Map<String,Object> map){
        return ResponseEntity.success(userService.changePassword(map.get("oldPassword").toString(),map.get("newPassword").toString()));
    }

    /**
     * 导入excel
     *
     * @param file 文件
     * @return {@link ResponseEntity<Boolean> }
     * @throws IOException ioexception
     * @author luox
     * @date 2021/07/23
     */
    @PostMapping("/importExcel")
    public ResponseEntity<Boolean> importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.success(userService.importExcel(file));
    }

    /**
     * 导出excel
     *
     * @param map      map
     * @param response 响应
     * @return
     * @throws IOException ioexception
     * @author luox
     * @date 2021/07/26
     */
    @PostMapping("/exportExcel")
    public void exportExcel(@RequestBody Map<String,Object> map, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = userService.exportExcel((List<User>) getUser(map).getData().get("data"));

        String currentTimeString = DateUtil.now();
        response.setCharacterEncoding("UTF-8");
        String fileName = "人员信息" + currentTimeString;
        //允许除默认的首部可以作为响应的一部分暴露给外部
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        //通过响应头告诉浏览器该文件为下载文件，并指定默认文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8") + ".xlsx");
        response.setContentType("application/octet-stream");

        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
