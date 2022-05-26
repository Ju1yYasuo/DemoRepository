package com.example.demo.util.redis;

import java.util.concurrent.TimeUnit;

/**
 * 过期枚举
 *
 * @author luox
 * @date 2022/4/2
 */
public enum ExpireEnum{
    //未读消息的有效期为30天
    UNREAD_MSG(30L, TimeUnit.DAYS)
    ;

    /**
     * 过期时间
     */
    private final Long time;
    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;

    ExpireEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
