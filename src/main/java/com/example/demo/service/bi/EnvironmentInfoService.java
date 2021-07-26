package com.example.demo.service.bi;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 环境信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-26
 */
public interface EnvironmentInfoService extends IService<EnvironmentInfo> {

    /**
     * 获取环境信息
     *
     * @param page        页面
     * @param fuzzySearch 模糊搜索
     * @return {@link List<EnvironmentInfo> }
     * @author luox
     * @date 2021-07-26
     */
    List<EnvironmentInfo> getEnvironmentInfo(Page<EnvironmentInfo> page, String fuzzySearch);

    /**
     * 保存环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    Boolean saveEnvironmentInfo(EnvironmentInfo environmentInfo);

    /**
     * 更新环境信息
     *
     * @param environmentInfo 环境信息
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    Boolean updateEnvironmentInfo(EnvironmentInfo environmentInfo);

    /**
     * 删除环境信息
     *
     * @param idList id列表
     * @return {@link Boolean }
     * @author luox
     * @date 2021-07-26
     */
    Boolean deleteEnvironmentInfo(List<Integer> idList);

}
