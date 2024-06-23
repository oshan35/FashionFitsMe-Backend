package com.example.VirtualFitON;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigLogger {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        System.out.println("Redis Host: " + redisHost);
        System.out.println("Redis Port: " + redisPort);
    }
}

