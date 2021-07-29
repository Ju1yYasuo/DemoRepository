package com.example.demo.service.bi;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 环境信息 服务类
 *
 * @author luox
 * @since 2021-07-29
 */
public interface EnvironmentInfoService extends IService<EnvironmentInfo> {

    /**
     * 获取环境信息
     *
     * @param page        页面
     * @param fuzzySearch 模糊搜索
     * @return {@link List<EnvironmentInfo> }
     * @author luox
     * @date 2021-07-29
     */
    List<EnvironmentInfo> getEnvironmentInfo(Page<EnvironmentInfo> page, String fuzzySearch);

    /**
     * 保存环境信息
     *
     * @param environmentInfo 环境信息
     * @return boolean
     * @author luox
     * @date 2021-07-29
     */
    boolean saveEnvironmentInfo(EnvironmentInfo environmentInfo);

    /**
     * 更新环境信息
     *
     * @param environmentInfo 环境信息
     * @return boolean
     * @author luox
     * @date 2021-07-29
     */
    boolean updateEnvironmentInfo(EnvironmentInfo environmentInfo);

    /**
     * 删除环境信息
     *
     * @param idList id列表
     * @return boolean
     * @author luox
     * @date 2021-07-29
     */
    boolean deleteEnvironmentInfo(List<Integer> idList);

}
