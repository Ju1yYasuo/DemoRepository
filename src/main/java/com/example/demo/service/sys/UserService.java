package com.example.demo.service.sys;

import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.sys.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人员信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-16
 */
public interface UserService extends IService<User> {

    /**
     * 获取人员信息
     *
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<User>> }
     * @author luox
     * @date 2021-07-16
     */
    ResponseEntity<List<User>> getUser(QueryEntity<User> queryEntity);

    /**
     * 保存人员信息
     *
     * @param user 人员信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-16
     */
    ResponseEntity<Boolean> saveUser(User user);

    /**
     * 更新人员信息
     *
     * @param user 人员信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-16
     */
    ResponseEntity<Boolean> updateUser(User user);

    /**
     * 删除人员信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-16
     */
    ResponseEntity<Boolean> deleteUser(List<Integer> idList);

}
