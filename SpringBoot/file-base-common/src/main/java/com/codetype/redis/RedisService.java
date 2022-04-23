package com.codetype.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    //存缓存
    public void set(String key,String value,Long timeOut){
        redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }
    //取缓存
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    //删除缓存
    public void del(String key){
        redisTemplate.delete(key);
    }
}
