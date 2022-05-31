package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.BiProducts;
import com.example.demo.mapper.BiProductsMapper;
import com.example.demo.service.BiProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.vo.BaseQueryVo;
import org.springframework.stereotype.Service;

/**
 * es-产品信息 服务实现类
 *
 * @author luox
 * @since 2022-05-31
 */
@Service
public class BiProductsServiceImpl extends ServiceImpl<BiProductsMapper, BiProducts> implements BiProductsService {

    @Override
    public Page<BiProducts> page(BaseQueryVo<BiProducts> pageVo, BiProducts biProducts){
        LambdaQueryWrapper<BiProducts> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.setEntity(biProducts);
        return page(pageVo.toPage(), queryWrapper);
    }
    
}
