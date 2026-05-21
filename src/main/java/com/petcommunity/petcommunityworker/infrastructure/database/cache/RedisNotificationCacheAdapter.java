package com.petcommunity.petcommunityworker.infrastructure.database.cache;

import com.petcommunity.petcommunityworker.application.in.notification.dto.NotificationListDto;
import com.petcommunity.petcommunityworker.application.out.cache.NotificationsCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisNotificationCacheAdapter implements NotificationsCachePort {

    private final RedisTemplate<String, NotificationListDto> notificationRedisTemplate;

    public static String getKey(Long id) {
        return "notification:" + id;
    }

    @Override
    public void create(Long id, NotificationListDto notificationListDto, int day) {
        notificationRedisTemplate.opsForList().rightPush(getKey(id), notificationListDto);
        notificationRedisTemplate.expire(getKey(id), Duration.ofDays(day));
    }
}
