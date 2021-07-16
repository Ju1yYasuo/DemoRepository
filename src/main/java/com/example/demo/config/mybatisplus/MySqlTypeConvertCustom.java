package com.example.demo.config.mybatisplus;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * 代码生成器 自定义类型转换
 * 继承MySqlTypeConvert for 需要使用其他方法变量
 * 实现ITypeConvert for 重写转换方法
 * @author luox
 * @date 2021/7/13
 */
public class MySqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
//        因为fieldName(M) zerofill ,数子型字段使用zerofill才会使M生效，位数不够左边填0 ，
//        而tinyint(1)为仅有一位二进制码所以 在各个框架被认为boolean
        if (fieldType.contains("tinyint") && !fieldType.equals("tinyint(1)")) {
            return DbColumnType.BYTE;
        }
        return super.processTypeConvert(config, fieldType);
    }
}
