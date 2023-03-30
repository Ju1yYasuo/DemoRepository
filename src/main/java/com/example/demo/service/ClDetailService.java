package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.ClDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.util.vo.BaseQueryVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 *  服务类
 *
 * @author luox
 * @since 2023-03-30
 */
public interface ClDetailService extends IService<ClDetail> {

    /**
     * 分页查询
     *
     * @param pageVo 分页vo
     * @param clDetail 
     * @return {@link Page<ClDetail> }
     * @author luox
     * @date 2023-03-30
     */
    Page<ClDetail> page(BaseQueryVo<ClDetail> pageVo, ClDetail clDetail);

    XSSFWorkbook getRepeatDetail() throws IOException;
}
