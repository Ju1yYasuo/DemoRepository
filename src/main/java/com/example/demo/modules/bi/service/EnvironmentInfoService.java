package com.example.demo.modules.bi.service;

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.bi.entity.EnvironmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 环境信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-13
 */
public interface EnvironmentInfoService extends IService<EnvironmentInfo> {

    ResponseEntity<List<EnvironmentInfo>> getEnvironmentInfo(QueryEntity<EnvironmentInfo> queryEntity);

    ResponseEntity<Boolean> saveEnvironmentInfo(List<EnvironmentInfo> list);

    ResponseEntity<Boolean> updateEnvironmentInfo(List<EnvironmentInfo> list);

    ResponseEntity<Boolean> deleteEnvironmentInfo(List<Integer> idList);

}
