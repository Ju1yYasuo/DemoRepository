package com.example.demo.util.eface;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.example.demo.config.source.EFaceDataSourceConfig;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * EFace查询工具类
 *
 * @author luox
 * @date 2021/9/1
 */
public class EFaceQueryUtil {

    private static final DataSource eFaceDataSource = EFaceDataSourceConfig.getEFaceDataSource();

    /**
     * 获取EFace人的实体类
     *
     * @param passType  通过类型
     * @param validCode 人员id
     * @return {@link Entity }
     * @throws SQLException sqlexception异常
     * @author luox
     * @date 2021/09/01
     */
    public static Entity getPersonEntity(String passType,String validCode) throws SQLException {
        Entity personEntity;
        if(passType.equals("10")){
            //访客
            personEntity = Db.use(eFaceDataSource).query(EFaceSql.GET_HRINFO_PERSON_VISITOR,validCode).get(0);
        }else{
            personEntity = Db.use(eFaceDataSource).query(EFaceSql.GET_HRINFO_PERSON,validCode).get(0);
        }
        return personEntity;
    }
}
