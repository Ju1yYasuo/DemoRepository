package com.example.demo.mapper.sys;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author luox
 * @date 2021/11/26
 */
public interface TestMapper {

    @Select("select * from knowledge_type")
    List<Object> test();

}
