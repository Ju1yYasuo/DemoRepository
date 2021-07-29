package com.example.demo.service.bi.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.example.demo.mapper.bi.EnvironmentInfoMapper;
import com.example.demo.service.bi.EnvironmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 环境信息 服务实现类
 *
 * @author luox
 * @since 2021-07-29
 */
@Service
public class EnvironmentInfoServiceImpl extends ServiceImpl<EnvironmentInfoMapper, EnvironmentInfo> implements EnvironmentInfoService {
    
    @Autowired
    private EnvironmentInfoMapper environmentInfoMapper;

    @Override
    public List<EnvironmentInfo> getEnvironmentInfo(Page<EnvironmentInfo> page, String fuzzySearch){
        List<EnvironmentInfo> list;

        QueryWrapper<EnvironmentInfo> queryWrapper = new QueryWrapper<>();

        if(page != null){
            list = page(page,queryWrapper).getRecords();
        }else{
            list = list(queryWrapper);
        }
        return list;
    }
    
    @Override
    public boolean saveEnvironmentInfo(EnvironmentInfo environmentInfo) {
        return save(environmentInfo);
    }

    @Override
    public boolean updateEnvironmentInfo(EnvironmentInfo environmentInfo) {
        return updateById(environmentInfo);
    }

    @Override
    public boolean deleteEnvironmentInfo(List<Integer> idList) {
        return removeByIds(idList);
    }
    
}
