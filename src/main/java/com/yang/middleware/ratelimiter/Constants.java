package com.yang.middleware.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/13
 * @Copyright：
 */
public class Constants {
    //public static Map<String, RateLimiter> rateLimiterMap = Collections.synchronizedMap(new HashMap<String, RateLimiter>());

    public static Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();
}
