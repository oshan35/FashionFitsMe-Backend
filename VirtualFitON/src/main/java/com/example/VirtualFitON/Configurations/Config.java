package com.example.VirtualFitON.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
// This annotation creates a filter that replaces the HttpSession implementation with one backed by Redis
public class Config {

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(); // Or configure connection settings if not default
    }

}