package com.example.demo.modules.test.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.modules.test.entity.Luoxtest;
import com.example.demo.modules.test.service.LuoxtestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/test/luoxtest")
//@Api(value = "swagger2的demo例子")
public class LuoxtestController {

    @Autowired
    private LuoxtestService luoxtestService;


    /**
     * 得到所有luoxtest
     *
     * @return {@link String }
     * @author luox
     * @date 2021/06/25
     */
    @GetMapping("/getAllLuoxtest")
    public String getAllLuoxtest(){
        return luoxtestService.getAllLuoxtest().toString();
    }

    /**
     * 保存luoxtest
     *
     * @param luoxtest luoxtest
     * @return {@link Long }
     * @throws Exception 异常
     * @author luox
     * @date 2021/06/28
     */
    @ApiOperation(value="保存luoxtest", notes="保存luoxtest,不需要传Id")
    @ApiImplicitParam(name = "asd", value = "jkl", required = true, dataType = "Luoxtest", paramType = "body")
    @PostMapping("/saveLuoxtest")
    public Long saveLuoxtest(@RequestBody Luoxtest luoxtest) throws Exception {
        return luoxtestService.saveLuoxtest(luoxtest);
    }


    /**
     * 通过id获取名称
     *
     * @param id id
     * @return {@link String }
     * @author luox
     * @date 2021/06/25
     */
    @GetMapping("/getNameById")
    public String getNameById(@RequestParam(value = "luoxtestId") Long id){
        return luoxtestService.getNameById(id);
    }

    @GetMapping("/getLuoxtestByName")
    public List<Luoxtest> getLuoxtestByName(@RequestParam(value = "name") String name,
                        //@RequestParam default required = true,otherwise throw exception
                        //defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false,如果没有传该参数，就使用默认值
                                            @RequestParam(value = "current") Long current,
                                            @RequestParam(value = "size") Long size){
        // Step1：创建一个 Page 对象
//        Page<Luoxtest> page = new Page<>();
//        // Page<User> page = new Page<>(2, 5);
//        // Step2：调用 mybatis-plus 提供的分页查询方法
//        // Step3：获取分页数据
//        System.out.println(page.getCurrent()); // 获取当前页
//        System.out.println(page.getTotal()); // 获取总记录数
//        System.out.println(page.getSize()); // 获取每页的条数
//        System.out.println(page.getRecords()); // 获取每页数据的集合
//        System.out.println(page.getPages()); // 获取总页数
//        System.out.println(page.hasNext()); // 是否存在下一页
//        System.out.println(page.hasPrevious()); // 是否存在上一页

        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return luoxtestService.getLuoxtestByName(page,name);
    }



    /**
     * 更新luoxtest名称由id
     *
     * @param id   id
     * @param name 的名字
     * @return {@link Boolean }
     * @throws Exception 异常
     * @author luox
     * @date 2021/06/25
     */
    @GetMapping("/updateLuoxtestNameById")
    public Boolean updateLuoxtestNameById(@RequestParam(value = "id") Long id,
                                          @RequestParam(value = "name") String name) throws Exception {
        return luoxtestService.updateLuoxtestNameById(id,name);
    }



}

