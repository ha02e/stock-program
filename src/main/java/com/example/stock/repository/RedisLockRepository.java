package com.example.stock.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisLockRepository {

    private RedisTemplate<String, String> redisTemplate;

    public RedisLockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //로직 실행 전 키와 setnx 명령어를 활용해 lock
    public Boolean lock(Long key){
        return redisTemplate
                .opsForValue()
                .setIfAbsent(gernerateKey(key), "lock", Duration.ofMillis(3_000));
    }

    //로직 실행 후 unlock 메소드를 통해 lock 해제
    public Boolean unlock(Long key){
        return redisTemplate.delete(gernerateKey(key));
    }

    private String gernerateKey(Long key){
        return key.toString();
    }
}
