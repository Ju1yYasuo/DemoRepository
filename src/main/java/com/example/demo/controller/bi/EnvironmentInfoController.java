package com.example.demo.controller.bi;

import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.example.demo.service.bi.EnvironmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 环境信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-19
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
     * @date 2021-07-19
     */
    @PostMapping("/getEnvironmentInfo")
    public ResponseEntity<List<EnvironmentInfo>> getEnvironmentInfo(@RequestBody QueryEntity<EnvironmentInfo> queryEntity) {
        return environmentInfoService.getEnvironmentInfo(queryEntity);
    }

    /**
     * 保存环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-19
     */
    @PostMapping("/saveEnvironmentInfo")
    public ResponseEntity<Boolean> saveEnvironmentInfo(@RequestBody EnvironmentInfo environmentInfo){
        return environmentInfoService.saveEnvironmentInfo(environmentInfo);
    }

    /**
     * 更新环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-19
     */
    @PostMapping("/updateEnvironmentInfo")
    public ResponseEntity<Boolean> updateEnvironmentInfo(@RequestBody EnvironmentInfo environmentInfo){
        return environmentInfoService.updateEnvironmentInfo(environmentInfo);
    }

    /**
     * 删除环境信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-19
     */
    @PostMapping("/deleteEnvironmentInfo")
    public ResponseEntity<Boolean> deleteEnvironmentInfo(@RequestBody List<Integer> idList){
        return environmentInfoService.deleteEnvironmentInfo(idList);
    }

}
