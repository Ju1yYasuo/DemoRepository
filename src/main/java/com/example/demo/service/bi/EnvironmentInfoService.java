package com.example.demo.service.bi;

import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 环境信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-19
 */
public interface EnvironmentInfoService extends IService<EnvironmentInfo> {

    /**
     * 获取环境信息
     *
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<EnvironmentInfo>> }
     * @author luox
     * @date 2021-07-19
     */
    ResponseEntity<List<EnvironmentInfo>> getEnvironmentInfo(QueryEntity<EnvironmentInfo> queryEntity);

    /**
     * 保存环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-19
     */
    ResponseEntity<Boolean> saveEnvironmentInfo(EnvironmentInfo environmentInfo);

    /**
     * 更新环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-19
     */
    ResponseEntity<Boolean> updateEnvironmentInfo(EnvironmentInfo environmentInfo);

    /**
     * 删除环境信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-19
     */
    ResponseEntity<Boolean> deleteEnvironmentInfo(List<Integer> idList);

}
