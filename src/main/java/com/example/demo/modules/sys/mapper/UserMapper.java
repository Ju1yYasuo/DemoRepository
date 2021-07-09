package com.example.demo.modules.sys.mapper;

import com.example.demo.modules.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author luox
 * @since 2021-07-08
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserBySex(@Param("sex") Integer sex);

}
