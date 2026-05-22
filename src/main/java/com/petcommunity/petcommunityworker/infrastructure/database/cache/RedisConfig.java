package com.petcommunity.petcommunityworker.infrastructure.database.cache;

import com.petcommunity.petcommunityworker.application.in.notification.dto.NotificationListDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
@Slf4j
@Getter
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean//알림을 위한 redisTemplate
    public RedisTemplate<String, NotificationListDto> notificationRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        JacksonJsonRedisSerializer<NotificationListDto> valSer =
                new JacksonJsonRedisSerializer<>(NotificationListDto.class);

        RedisTemplate<String, NotificationListDto> notificationRedisTemplate = new RedisTemplate<>();
        notificationRedisTemplate.setConnectionFactory(redisConnectionFactory);
        notificationRedisTemplate.setKeySerializer(new StringRedisSerializer());
        notificationRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        notificationRedisTemplate.setValueSerializer(valSer);
        notificationRedisTemplate.setHashValueSerializer(valSer);
        notificationRedisTemplate.afterPropertiesSet();
        return notificationRedisTemplate;
    }
}
