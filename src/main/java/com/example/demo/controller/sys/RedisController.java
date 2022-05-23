package com.example.demo.controller.sys;

import com.example.demo.config.annotation.ResponseEntity;
import com.example.demo.util.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis测试
 *
 * @author luox
 * @date 2022/4/2
 */
@RestController
@RequestMapping("/redis")
@Slf4j
@ResponseEntity
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/addRedisData")
    public Boolean addRedisData(@RequestParam("cacheKey") String cacheKey,
                                                @RequestParam("cacheValue") String cacheValue){
        redisUtil.set(cacheKey,cacheValue);
        return true;
    }

    @GetMapping("/getRedisData")
    public Object getRedisData(@RequestParam("cacheKey") String cacheKey){
        return redisUtil.get(cacheKey);
    }

    /**
     * 获取redis cache,cacheKey = $key,redisKey = $cacheName::$key
     *
     * @param cacheKey 缓存键
     * @return {@link Object }
     * @author luox
     * @date 2022/04/06
     */
    @GetMapping("/getRedisCache")
    @Cacheable(value = "redisCache",key = "#cacheKey")
    public Object getRedisCache(@RequestParam("cacheKey") String cacheKey){
        log.info("getRedisCache");
        return redisUtil.get(cacheKey);
    }

    @GetMapping("/addRedisCache")
    @CacheEvict(value = "redisCache",key = "#cacheKey")
    public Boolean addRedisCache(@RequestParam("cacheKey") String cacheKey,
                                                 @RequestParam("cacheValue") String cacheValue){
        log.info("addRedisCache");
        redisUtil.set(cacheKey,cacheValue);
        return true;
    }

}
