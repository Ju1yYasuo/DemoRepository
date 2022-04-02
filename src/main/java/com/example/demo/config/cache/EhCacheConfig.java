package com.example.demo.config.cache;

import com.example.demo.util.common.Constant;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * ehcache缓存配置
 *
 * @author luox
 * @date 2022/03/25
 */
//@Configuration
public class EhCacheConfig {

    @Autowired
    private CacheManager cacheManager;


    /**
     * 添加角色、权限、用户缓存
     *
     * @author luox
     * @date 2022/01/27
     */
    @PostConstruct
    public void addPermissionCache(){
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setMaxEntriesLocalHeap(100000);
        cacheConfiguration.setMaxElementsOnDisk(1000000);
        //永驻内存
        cacheConfiguration.setEternal(false);
        //缓存自创建日期起至失效时的间隔时间
        cacheConfiguration.setTimeToLiveSeconds(0);
        //缓存创建以后，最后一次访问缓存的日期至失效之时的时间间隔
        cacheConfiguration.setTimeToIdleSeconds(12 * 3600);
        cacheConfiguration.setOverflowToDisk(true);
        //新增权限缓存
        cacheConfiguration.setName(Constant.USER_PERMISSION_CACHE);
        Cache userPermissionCache = new Cache(cacheConfiguration);
        cacheManager.addCache(userPermissionCache);
        //新增角色缓存
        cacheConfiguration.setName(Constant.USER_ROLE_CACHE);
        Cache userRoleCache = new Cache(cacheConfiguration);
        cacheManager.addCache(userRoleCache);
        //新增用户缓存
        cacheConfiguration.setName(Constant.USER_CACHE);
        Cache userCache = new Cache(cacheConfiguration);
        cacheManager.addCache(userCache);
        //新增用户登录错误次数缓存
        cacheConfiguration.setName(Constant.USER_LOGIN_ERROR_TIMES);
        Cache userLoginErrorTimesCache = new Cache(cacheConfiguration);
        cacheManager.addCache(userLoginErrorTimesCache);
    }

}
