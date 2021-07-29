package com.example.demo.controller.bi;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.example.demo.service.bi.EnvironmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 环境信息 前端控制器
 *
 * @author luox
 * @since 2021-07-29
 */
@RestController
@RequestMapping("/bi/environment-info")
public class EnvironmentInfoController {

    @Autowired
    private EnvironmentInfoService environmentInfoService;

    /**
     * 获取环境信息
     *
     * @param map map
     * @return {@link ResponseEntity<List<EnvironmentInfo>> }
     * @author luox
     * @date 2021-07-29
     */
    @PostMapping("/getEnvironmentInfo")
    public ResponseEntity<List<EnvironmentInfo>> getEnvironmentInfo(@RequestBody Map<String,Object> map) {
            Page<EnvironmentInfo> page = null;
            String fuzzySearch = null;
            if(map.containsKey("current") && map.containsKey("size")){
                page = new Page<>();
                page.setCurrent(Long.parseLong(String.valueOf(map.get("current"))));
                page.setSize(Long.parseLong(String.valueOf(map.get("size"))));
            }
            if(map.containsKey("fuzzySearch")){
                fuzzySearch = (String) map.get("fuzzySearch");
            }
            return ResponseEntity.success(environmentInfoService.getEnvironmentInfo(page,fuzzySearch));
        }

    /**
     * 保存环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-29
     */
    @PostMapping("/saveEnvironmentInfo")
    public ResponseEntity<Boolean> saveEnvironmentInfo(@RequestBody EnvironmentInfo environmentInfo){
        return ResponseEntity.success(environmentInfoService.saveEnvironmentInfo(environmentInfo));
    }

    /**
     * 更新环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-29
     */
    @PostMapping("/updateEnvironmentInfo")
    public ResponseEntity<Boolean> updateEnvironmentInfo(@RequestBody EnvironmentInfo environmentInfo){
        return ResponseEntity.success(environmentInfoService.updateEnvironmentInfo(environmentInfo));
    }

    /**
     * 删除环境信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-29
     */
    @PostMapping("/deleteEnvironmentInfo")
    public ResponseEntity<Boolean> deleteEnvironmentInfo(@RequestBody List<Integer> idList){
        return ResponseEntity.success(environmentInfoService.deleteEnvironmentInfo(idList));
    }

}
