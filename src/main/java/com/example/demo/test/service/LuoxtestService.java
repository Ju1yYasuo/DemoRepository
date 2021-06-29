package com.example.demo.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.test.entity.Luoxtest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luox
 * @since 2021-06-15
 */
public interface LuoxtestService extends IService<Luoxtest> {

    List<Luoxtest> getAllLuoxtest();


    Long saveLuoxtest(Luoxtest luoxtest) throws Exception;

    String getNameById(Long id);

    List<Luoxtest> getLuoxtestByName(Page page, String name);


    Boolean updateLuoxtestNameById(Long id,String name) throws Exception;

}
