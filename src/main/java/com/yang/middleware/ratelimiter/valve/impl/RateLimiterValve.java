package com.yang.middleware.ratelimiter.valve.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.yang.middleware.ratelimiter.Constants;
import com.yang.middleware.ratelimiter.annotation.DoRateLimiter;
import com.yang.middleware.ratelimiter.valve.IValveService;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/14
 * @Copyright：
 */
public class RateLimiterValve implements IValveService {
    @Override
    public Object access(ProceedingJoinPoint joinPoint, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable {
        // 未设置限流量，则不限流
        if (0 == doRateLimiter.permitsPerSecond()) {
            return joinPoint.proceed();
        }
        String methodName = method.getName();
        String clazzName = joinPoint.getTarget().getClass().getName();
        String key = clazzName + "." + methodName;
        if (null == Constants.rateLimiterMap.get(key)) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }
        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        }
        return JSON.parseObject(doRateLimiter.returnJSON(), method.getReturnType());
    }
}
