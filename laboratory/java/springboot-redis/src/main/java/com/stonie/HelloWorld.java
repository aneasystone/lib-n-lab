package com.stonie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String ACCESS_COUNTER_KEY = "access-counter";

    @RequestMapping("/")
    public String index() {
        int accessCount = 0;
        String accessCountStr = (String) redisTemplate.opsForValue().get(ACCESS_COUNTER_KEY);
        if (accessCountStr != null) {
            accessCount = Integer.parseInt(accessCountStr);
        }
        accessCount = accessCount + 1;
        redisTemplate.opsForValue().set(ACCESS_COUNTER_KEY, String.valueOf(accessCount));
        return "Hello World, This page has bean visited " + accessCount + " times.";
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ACCESS_COUNTER_KEY_2 = "access-counter-2";

    @RequestMapping("/2")
    public String index2() {
        int accessCount = 0;
        String accessCountStr = (String) stringRedisTemplate.opsForValue().get(ACCESS_COUNTER_KEY_2);
        if (accessCountStr != null) {
            accessCount = Integer.parseInt(accessCountStr);
        }
        accessCount = accessCount + 1;
        stringRedisTemplate.opsForValue().set(ACCESS_COUNTER_KEY_2, String.valueOf(accessCount));
        return "Hello World 2, This page has bean visited " + accessCount + " times.";
    }
}
