package com.petcommunity.petcommunityworker.infrastructure.database.redis;

import com.petcommunity.petcommunityworker.application.out.cache.AppOnlineCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisAppOnlineRepository implements AppOnlineCachePort {

    private final StringRedisTemplate redisTemplate;

    // Foreground members
    private static String getKey() {
        return "foreGroundMembers";
    }

    //todo : websocket connect할 때 유저 저장하면 될 것 같은데...
    @Override
    public Boolean exist(Long id) {
        return redisTemplate.opsForSet().isMember(getKey(), id.toString());
    }
}
