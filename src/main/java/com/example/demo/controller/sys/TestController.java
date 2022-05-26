package com.example.demo.controller.sys;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.annotation.ResponseEntity;
import com.example.demo.mapper.sys.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 多数据源测试
 *
 * @author luox
 * @date 2021/11/26
 */
//@RestController
@RequestMapping("/test")
@Slf4j
@ResponseEntity
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @DS("ds1")
    @GetMapping("/test")
    public List<Map<String,Object>> test(){
        return testMapper.test();
    }

    @DS("dm1")
    @GetMapping("/testDM")
    public List<Map<String,Object>> testDM(){
        return testMapper.testDM();
    }

    @DS("dm1")
    @GetMapping("/testDM2")
    public List<Map<String,Object>> testDM2(){
        Page<Object> page = new Page<>();
        page.setCurrent(1);
        page.setSize(5);
        List<Map<String,Object>> list = testMapper.testDM2(page);
        System.out.println("DM DataBase Log:");
        log.info(list.toString());
        return list;
    }
}
