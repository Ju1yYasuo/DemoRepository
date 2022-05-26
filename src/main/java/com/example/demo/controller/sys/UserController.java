package com.example.demo.controller.sys;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.annotation.ControllerAnn;
import com.example.demo.config.annotation.ResponseEntity;
import com.example.demo.dto.sys.UserLoginDto;
import com.example.demo.dto.sys.UserLoginResultDto;
import com.example.demo.entity.sys.User;
import com.example.demo.service.sys.UserService;
import com.example.demo.util.vo.BaseQueryVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@ResponseEntity
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取人员信息
     *
     * @param pageVo 页签证官
     * @param user   用户
     * @return {@link Page<User> }
     * @author luox
     * @date 2022/03/24
     */
    @GetMapping("/getUser")
    //@SaCheckPermission("sys:user:list")
    public Page<User> getUser(@Validated BaseQueryVo<User> pageVo, User user) {
        //Page<User> page = null;
        //String fuzzySearch = null;
        //if(map.containsKey("current") && map.containsKey("size")){
        //    page = new Page<>();
        //    page.setCurrent(Long.parseLong(String.valueOf(map.get("current"))));
        //    page.setSize(Long.parseLong(String.valueOf(map.get("size"))));
        //}
        //if(map.containsKey("fuzzySearch")){
        //    fuzzySearch = (String) map.get("fuzzySearch");
        //}
        return userService.getUser(pageVo,user);
    }

    /**
     * 保存人员信息
     *
     * @param user 人员信息
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    @PostMapping("/saveUser")
    public Boolean saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    /**
     * 更新人员信息
     *
     * @param user 人员信息
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    @PostMapping("/deleteUser")
    public Boolean deleteUser(@RequestBody List<Integer> idList){
        return userService.deleteUser(idList);
    }

    /**
     * 登录
     *
     * @param dto dto
     * @return {@link UserLoginResultDto }
     * @author luox
     * @date 2022/05/26
     */
    @PostMapping("/login")
    public UserLoginResultDto login(@RequestBody UserLoginDto dto) {
        return userService.login(dto.getUserName(),dto.getPassword());
    }

    /**
     * 注销
     *
     * @return {@link Boolean }
     * @author luox
     * @date 2021/07/28
     */
    @GetMapping("/logOut")
    public Boolean logOut() {
        return userService.logOut();
    }

    /**
     * 导入excel
     *
     * @param file 文件
     * @return {@link Boolean }
     * @throws IOException ioexception
     * @author luox
     * @date 2021/07/23
     */
    @PostMapping("/importExcel")
    public Boolean importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return userService.importExcel(file);
    }

    /**
     * 导出excel
     *
     * @param user     用户
     * @param response 响应
     * @throws IOException ioexception
     * @author luox
     * @date 2022/03/24
     */
    @PostMapping("/exportExcel")
    public void exportExcel(@RequestBody User user, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = userService.exportExcel(userService.getUser(user));

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
