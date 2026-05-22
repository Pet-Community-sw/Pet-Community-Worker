package com.petcommunity.petcommunityworker.infrastructure.database.cache.adapter;

import com.petcommunity.petcommunityworker.application.out.cache.EmailCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisEmailCacheAdapter implements EmailCachePort {

    private final StringRedisTemplate template;

    @Override
    public void createWithDuration(String key, String value, long duration) {
        template.opsForValue().set(key, value, Duration.ofSeconds(duration));
    }

    @Override
    public boolean exist(String key) {
        return template.hasKey(key);
    }

    @Override
    public void delete(String key) {
        template.delete(key);
    }

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }
}
