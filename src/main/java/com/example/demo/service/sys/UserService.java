package com.example.demo.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.sys.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param page        页面
     * @param fuzzySearch 模糊搜索
     * @return {@link List<User> }
     * @author luox
     * @date 2021-07-26
     */
    List<User> getUser(Page<User> page, String fuzzySearch);

    /**
     * 保存人员信息
     *
     * @param user 人员信息
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    Boolean saveUser(User user);

    /**
     * 更新人员信息
     *
     * @param user 人员信息
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    Boolean updateUser(User user);

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    Boolean deleteUser(List<Integer> idList);

}
