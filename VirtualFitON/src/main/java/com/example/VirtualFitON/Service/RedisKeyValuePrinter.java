//package com.example.VirtualFitON.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//public class RedisKeyValuePrinter {
//
//    @Autowired
//    private RedisTemplate<String, > redisTemplate;
//
//    public void printAllKeysAndValues() {
//        // Retrieve all keys
//        Set<String> keys = redisTemplate.keys("*");
//
//        // Iterate through each key and print its value
//        if (keys != null) {
//            for (String key : keys) {
//                String value = redisTemplate.opsForValue().get(key);
//                System.out.println("Key: " + key + ", Value: " + value);
//            }
//        } else {
//            System.out.println("No keys found in Redis.");
//        }
//    }
//}
