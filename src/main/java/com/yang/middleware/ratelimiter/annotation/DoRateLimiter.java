package com.yang.middleware.ratelimiter.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/13
 * @Copyright：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoRateLimiter {

    double permitsPerSecond() default 0D;   // 限流许可量
    String returnJSON() default "";         // 失败结果JSON

}
