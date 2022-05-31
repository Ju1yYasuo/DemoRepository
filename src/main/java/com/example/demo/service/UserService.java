package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.UserLoginResultDto;
import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.util.vo.BaseQueryVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 人员信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-26
 */
public interface UserService extends IService<User> {

    /**
     * 获取人员信息
     *
     * @param pageVo 页签证官
     * @param user   用户
     * @return {@link Page<User> }
     * @author luox
     * @date 2022/03/24
     */
    Page<User> getUser(BaseQueryVo<User> pageVo, User user);

    /**
     * 获取用户
     *
     * @param user 用户
     * @return {@link List<User> }
     * @author luox
     * @date 2022/03/24
     */
    List<User> getUser(User user);

    /**
     * 保存人员信息
     *
     * @param user 人员信息
     * @return boolean
     * @author luox
     * @date 2021-07-26
     */
    boolean saveUser(User user);

    /**
     * 更新人员信息
     *
     * @param user 人员信息
     * @return boolean
     * @author luox
     * @date 2021-07-26
     */
    boolean updateUser(User user);

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return boolean
     * @author luox
     * @date 2021-07-26
     */
    boolean deleteUser(List<Integer> idList);

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return {@link UserLoginResultDto }
     * @author luox
     * @date 2022/05/26
     */
    UserLoginResultDto login(String userName, String password);

    /**
     * 注销
     *
     * @return boolean
     * @author luox
     * @date 2021/07/28
     */
    boolean logOut();

    /**
     * 更改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return boolean
     * @author luox
     * @date 2021/07/28
     */
    boolean changePassword(String oldPassword,String newPassword);

    /**
     * 导入excel
     *
     * @param file 文件
     * @return boolean
     * @throws IOException ioexception
     * @author luox
     * @date 2021/07/23
     */
    boolean importExcel(MultipartFile file) throws IOException;

    /**
     * 导出excel
     *
     * @param list 列表
     * @return {@link XSSFWorkbook }
     * @throws IOException ioexception
     * @author luox
     * @date 2021/07/26
     */
    XSSFWorkbook exportExcel(List<User> list) throws IOException;


}
