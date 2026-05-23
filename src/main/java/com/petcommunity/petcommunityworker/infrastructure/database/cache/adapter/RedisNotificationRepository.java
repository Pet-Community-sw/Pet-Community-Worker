package com.petcommunity.petcommunityworker.infrastructure.database.cache.adapter;

import com.petcommunity.petcommunityworker.application.out.cache.NotificationsCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisNotificationRepository implements NotificationsCachePort {

    private final StringRedisTemplate template;

    public static String getKey(Long id) {
        return "notification:" + id;
    }

    @Override
    public void create(Long id, String message, int day) {
        template.opsForList().rightPush(getKey(id), message);
        template.expire(getKey(id), Duration.ofDays(day));
    }
}
