package com.example.demo.config.mybatisplus;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.demo.entity.sys.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段处理器
 * @author luox
 * @date 2021/6/24
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentTime = new Date();
        this.strictInsertFill(metaObject, "createTime", Date.class, currentTime);
        //handler所有策略默认是有值则不替代
        this.strictInsertFill(metaObject, "updateTime", Date.class, currentTime);
        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
//        乐观锁，默认为1
//      this.strictInsertFill(metaObject, "version", Integer.class, 1);

        if(metaObject.getOriginalObject() instanceof User){
            this.strictInsertFill(metaObject, "password", String.class, DigestUtil.md5Hex("123456"));
            this.strictInsertFill(metaObject, "accountExpirationDate", Date.class, DateUtil.parseDate("2022-10-01"));
            this.strictInsertFill(metaObject, "logErrorNumber", Integer.class, 0);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

}