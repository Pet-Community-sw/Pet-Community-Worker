package com.petcommunity.petcommunityworker.infrastructure.database.cache.adapter;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.out.cache.EmailCachePort;
import com.petcommunity.petcommunityworker.application.usecase.email.EmailCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisEmailRepository implements EmailCachePort {

    private final StringRedisTemplate template;
    private final JsonUtil jsonUtil;

    @Override
    public void create(String key, EmailCode emailCode, long duration) {
        template.opsForValue().set(key, jsonUtil.toJson(emailCode), Duration.ofMinutes(duration));
    }

    @Override
    public EmailCode get(String key) {
        String object = template.opsForValue().get(key);
        if (object == null) return null;
        return jsonUtil.fromJson(object, EmailCode.class);
    }
}
