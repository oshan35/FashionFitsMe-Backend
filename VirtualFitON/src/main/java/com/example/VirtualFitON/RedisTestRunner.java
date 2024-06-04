package com.example.VirtualFitON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTestRunner implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            redisTemplate.opsForValue().set("test", "connection successful");
            String result = redisTemplate.opsForValue().get("test");
            System.out.println("Redis connection test result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to Redis: " + e.getMessage());
        }
    }
}
