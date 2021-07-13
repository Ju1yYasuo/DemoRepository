package com.example.demo.modules.bi.controller;

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.bi.entity.EnvironmentInfo;
import com.example.demo.modules.bi.service.EnvironmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 环境信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/bi/environment-info")
public class EnvironmentInfoController {

    @Autowired
    private EnvironmentInfoService environmentInfoService;

    /**
     * 获取环境信息
     *
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<EnvironmentInfo>> }
     * @author luox
     * @date 2021-07-13
     */
    @PostMapping("/getEnvironmentInfo")
    public ResponseEntity<List<EnvironmentInfo>> getEnvironmentInfo(@RequestBody QueryEntity<EnvironmentInfo> queryEntity) {
        return environmentInfoService.getEnvironmentInfo(queryEntity);
    }

    /**
     * 保存环境信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-13
     */
    @PostMapping("/saveEnvironmentInfo")
    @Transactional
    public ResponseEntity<Boolean> saveEnvironmentInfo(@RequestBody List<EnvironmentInfo> list){
        return environmentInfoService.saveEnvironmentInfo(list);
    }

    /**
     * 更新环境信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-13
     */
    @PutMapping("/updateEnvironmentInfo")
    @Transactional
    public ResponseEntity<Boolean> updateEnvironmentInfo(@RequestBody List<EnvironmentInfo> list){
        return environmentInfoService.updateEnvironmentInfo(list);
    }

    /**
     * 删除环境信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-13
     */
    @DeleteMapping("/deleteEnvironmentInfo")
    @Transactional
    public ResponseEntity<Boolean> deleteEnvironmentInfo(@RequestBody List<Integer> idList){
        return environmentInfoService.deleteEnvironmentInfo(idList);
    }

}
