package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.BiProducts;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.util.vo.BaseQueryVo;

/**
 * es-产品信息 服务类
 *
 * @author luox
 * @since 2022-05-31
 */
public interface BiProductsService extends IService<BiProducts> {

    /**
     * 分页查询es-产品信息
     *
     * @param pageVo 分页vo
     * @param biProducts es-产品信息
     * @return {@link Page<BiProducts> }
     * @author luox
     * @date 2022-05-31
     */
    Page<BiProducts> page(BaseQueryVo<BiProducts> pageVo, BiProducts biProducts);
}
