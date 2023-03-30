package com.example.demo.config.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 调度服务
 *
 * @author luox
 * @date 2021/7/27
 */
//@Component
//@EnableScheduling
public class ScheduleService {

    //@Autowired
    //private BiInandoutDeptService biInandoutDeptService;

    /**
     * 同步eFace部门信息
     *
     * @author luox
     * @date 2021/07/27
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void eFaceDepartmentSync(){
        System.out.println("eFaceDepartmentSync启动");
        //biInandoutDeptService.eFaceDepartmentSync();
    }

}
