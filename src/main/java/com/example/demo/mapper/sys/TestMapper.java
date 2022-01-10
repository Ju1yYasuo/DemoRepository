package com.example.demo.mapper.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author luox
 * @date 2021/11/26
 */
public interface TestMapper {

    @Select("select ID, NAME, CODE, KEYWORDS, INSERT_DATE, UPDATE_DATE from knowledge_type")
    List<Map<String,Object>> test();

    @Select("SELECT banner as version_info FROM v$version")
    List<Map<String,Object>> testDM();

    @Select("select city_id,city_name,CITY.REGION_ID from dmhr.city")
    List<Map<String,Object>> testDM2(Page<Object> page);
}
