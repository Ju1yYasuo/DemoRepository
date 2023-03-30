package com.example.demo.mapper;

import com.example.demo.entity.ClDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author luox
 * @since 2023-03-30
 */
public interface ClDetailMapper extends BaseMapper<ClDetail> {

    List<ClDetail> getRepeatDetail();

}
