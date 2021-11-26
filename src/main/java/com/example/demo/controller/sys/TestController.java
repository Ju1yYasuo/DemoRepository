package com.example.demo.controller.sys;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.mapper.sys.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 多数据源测试
 *
 * @author luox
 * @date 2021/11/26
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @DS("ds1")
    @GetMapping("/test")
    public List<Object> test(){
        return testMapper.test();
    }

}
