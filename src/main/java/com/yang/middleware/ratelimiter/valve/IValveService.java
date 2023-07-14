package com.yang.middleware.ratelimiter.valve;

import com.yang.middleware.ratelimiter.annotation.DoRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/13
 * @Copyright：
 */
public interface IValveService {
    Object access(ProceedingJoinPoint joinPoint, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable;
}
