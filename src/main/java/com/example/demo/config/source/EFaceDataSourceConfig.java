package com.example.demo.config.source;

import cn.hutool.db.ds.DSFactory;
import com.example.demo.util.common.Constant;

import javax.sql.DataSource;

/**
 * eFace数据源配置
 *
 * @author luox
 * @date 2021/8/26
 */
public class EFaceDataSourceConfig {

    private volatile static DataSource eFaceDataSource = null;

    public static DataSource getEFaceDataSource() {
        if(eFaceDataSource == null){
            synchronized(EFaceDataSourceConfig.class){
                if(eFaceDataSource == null){
                    eFaceDataSource = DSFactory.get(Constant.EFACE_DATASOURCE_NAME);
                }
            }
        }
        return eFaceDataSource;
    }

}
