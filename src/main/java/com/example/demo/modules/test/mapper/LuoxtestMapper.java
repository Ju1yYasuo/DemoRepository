package com.example.demo.modules.test.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.modules.test.entity.Luoxtest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luox
 * @since 2021-06-15
 */
@Repository
public interface LuoxtestMapper extends BaseMapper<Luoxtest> {

//    用mybatis的insert 不能生成plus的主键生成策略，只能用继承的方法来保存
    @Insert("INSERT INTO `luoxtest` (`id`, `name`, `age`, `email`) VALUES " +
            "(#{luoxtest.id},#{luoxtest.name}, #{luoxtest.age}, #{luoxtest.email});")
//    useGeneratedKeys=true表示使用数据库自动增长的主键，keyColumn用于指定数据库table中的主键，keyProperty用于指定传入对象的成员变量
//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Long insertLuoxtest(@Param("luoxtest") Luoxtest luoxtest);


    String getNameById(@Param("luoxtestId") Long id);

    @Options(useCache = true,flushCache = Options.FlushCachePolicy.FALSE,timeout = 10000)
    IPage<Luoxtest> getLuoxtestByName(@Param("page")Page page, @Param("name") String name);

}
