package com.example.demo.config.source;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.demo.util.common.BeanUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * EFace mybatis-plus 配置数据源
 * 暂时不用
 *
 * @author luox
 * @date 2021/8/20
 */
public class EFaceBatisPlusConfig {

    private volatile static SqlSessionFactory eFaceSqlSessionFactory = null;
    private volatile static DataSource eFaceDataSource = null;

    public static SqlSessionFactory getEFaceSqlSessionFactory() {
        if(eFaceSqlSessionFactory == null){
            synchronized(EFaceBatisPlusConfig.class){
                if(eFaceSqlSessionFactory == null){
                    eFaceSqlSessionFactory = setEFaceSqlSessionFactory();
                }
            }
        }
        return eFaceSqlSessionFactory;
    }

    private static DataSource getEFaceDataSource() {
        if(eFaceDataSource == null){
            synchronized(EFaceBatisPlusConfig.class){
                if(eFaceDataSource == null){
                    eFaceDataSource = setEFaceDataSource();
                }
            }
        }
        return eFaceDataSource;
    }

    public static DataSource setEFaceDataSource(){
       Environment env = BeanUtil.getBean(Environment.class);
       if (StringUtils.isEmpty(env.getProperty("eFace.datasource.url"))) {
           return null;
       }
       HikariDataSource ds = new HikariDataSource();
       ds.setJdbcUrl(env.getProperty("eFace.datasource.url"));
       ds.setUsername(env.getProperty("eFace.datasource.username"));
       ds.setPassword(env.getProperty("eFace.datasource.password"));
       ds.setDriverClassName(env.getProperty("eFace.datasource.driver"));
       ds.setMaximumPoolSize(50);
       ds.setMinimumIdle(4);
       if (!StringUtils.isEmpty(env.getProperty("eFace.datasource.connectionInitSqls", ""))) {
           ds.setConnectionInitSql(env.getProperty("eFace.datasource.connectionInitSqls"));
       }
       return ds;
    }

    public static SqlSessionFactory setEFaceSqlSessionFactory() {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        DataSource eFaceDataSource = getEFaceDataSource();
        sqlSessionFactory.setDataSource(eFaceDataSource);
        //配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        try {
            sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                    getResources("classpath*:/mapper/**/*.xml"));
        } catch (IOException e) {
            System.out.println("getEFaceSqlSessionFactory 失败" + e);
            return null;
        }

        ////分页
        //PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //paginationInnerInterceptor.setDbType(DbType.MYSQL);
        //MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //mybatisPlusInterceptor.setInterceptors(CollUtil.newArrayList(paginationInnerInterceptor));
        //sqlSessionFactory.setPlugins(mybatisPlusInterceptor);
        ////全局
        //GlobalConfig globalConfig = new GlobalConfig();
        //MetaObjectHandler meteBean = BeanUtil.getBean(MetaObjectHandler.class);
        //globalConfig.setMetaObjectHandler(meteBean);
        //sqlSessionFactory.setGlobalConfig(globalConfig);
        //事务工厂
        //TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        //TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //TransactionFactory transactionFactory = new ManagedTransactionFactory();
        //transactionFactory.newTransaction(eFaceDataSource, TransactionIsolationLevel.REPEATABLE_READ,false);
        //sqlSessionFactory.setTransactionFactory(transactionFactory);
        try {
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            System.out.println("getEFaceSqlSessionFactory 失败" + e);
        }
        return null;
    }

    //事务管理器
    public static DataSourceTransactionManager getDataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(getEFaceDataSource());
        return dataSourceTransactionManager;
    }

    //模板事务
    public static TransactionTemplate getTransactionTemplate(){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(getDataSourceTransactionManager());
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        return transactionTemplate;
    }

}
