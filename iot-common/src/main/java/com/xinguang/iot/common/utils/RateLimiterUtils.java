package com.xinguang.iot.common.utils;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jinx on 2017/5/12.
 */
public class RateLimiterUtils {
    private static Logger logger = LoggerFactory.getLogger(RateLimiterUtils.class);

    private static final ConcurrentHashMap<String, RateLimiter> resourceLimitMap = new ConcurrentHashMap<String, RateLimiter>();


    /**
     * 限流
     * @param resource 需要限流的对象的标识
     * @return true表示得到了许可，没有达到限流阀值，false表示得不到许可，达到了限流阀值。
     */
    public static boolean rateLimit(String resource) {
        RateLimiter limit = getRateLimit(resource);
        return limit.tryAcquire();
    }
    /**
     * 获取某个需限流对象的RateLimiter，如不存在则创建新的
     */
    public static RateLimiter getRateLimit(String resource) {
        RateLimiter limit = resourceLimitMap.get(resource);
        if(limit == null) {
            synchronized(RateLimiterUtils.class) {
                limit = resourceLimitMap.get(resource);
//                double qps = getQpsByResource(resource);
                if(limit == null) {
                    limit = RateLimiter.create(1000);
                    resourceLimitMap.put(resource, limit);
                    logger.info("create rate limiter for key:{0},QPS:{1}", resource,1000);
                }
            }
        }
        return resourceLimitMap.get(resource);
    }

}
