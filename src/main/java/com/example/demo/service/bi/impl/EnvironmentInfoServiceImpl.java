package com.example.demo.service.bi.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.bi.EnvironmentInfo;
import com.example.demo.mapper.bi.EnvironmentInfoMapper;
import com.example.demo.service.bi.EnvironmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 环境信息 服务实现类
 * </p>
 *
 * @author luox
 * @since 2021-07-19
 */
@Service
public class EnvironmentInfoServiceImpl extends ServiceImpl<EnvironmentInfoMapper, EnvironmentInfo> implements EnvironmentInfoService {
    
    @Autowired
    private EnvironmentInfoMapper environmentInfoMapper;

    @Override
    public ResponseEntity<List<EnvironmentInfo>> getEnvironmentInfo(QueryEntity<EnvironmentInfo> queryEntity){
        Page<EnvironmentInfo> page = new Page<>();
        page.setCurrent(queryEntity.getCurrent());
        page.setSize(queryEntity.getSize());

        QueryWrapper<EnvironmentInfo> queryWrapper = new QueryWrapper<>();

        List<EnvironmentInfo> list = page(page,queryWrapper).getRecords();
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,list);
    }
    
    @Override
    public ResponseEntity<Boolean> saveEnvironmentInfo(EnvironmentInfo environmentInfo) {
        boolean result = save(environmentInfo);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> updateEnvironmentInfo(EnvironmentInfo environmentInfo) {
        boolean result = updateById(environmentInfo);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> deleteEnvironmentInfo(List<Integer> idList) {
        boolean result = removeByIds(idList);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }
    
}
