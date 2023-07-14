package com.yang.middleware.ratelimiter;

import com.yang.middleware.ratelimiter.annotation.DoRateLimiter;
import com.yang.middleware.ratelimiter.valve.IValveService;
import com.yang.middleware.ratelimiter.valve.impl.RateLimiterValve;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/13
 * @Copyright：
 */
@Aspect
@Component
public class DoRateLimiterPoint {

    @Pointcut("@annotation(com.yang.middleware.ratelimiter.annotation.DoRateLimiter)")
    public void aopPoint(){
    }

    @Around("aopPoint() && @annotation(doRateLimiter)")
    public Object doRouter(ProceedingJoinPoint joinPoint, DoRateLimiter doRateLimiter) throws Throwable {
        RateLimiterValve rateLimiterValve = new RateLimiterValve();
        return rateLimiterValve.access(joinPoint, getMethod(joinPoint), doRateLimiter,joinPoint.getArgs());
    }

    private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }
}
