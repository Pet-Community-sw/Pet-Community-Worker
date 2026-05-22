package com.petcommunity.petcommunityworker.infrastructure.stomp;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.out.SendPort;
import com.petcommunity.petcommunityworker.application.usecase.notification.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompAdapter implements SendPort {

    private static final String CHANNEL = "notification-channel";

    private final StringRedisTemplate template;
    private final JsonUtil jsonUtil;

    @Override
    public void send(String destination, NotificationDto notificationDto) {

        template.convertAndSend(CHANNEL,
                jsonUtil.toJson(new RedisNotificationMessage(destination, notificationDto)));
        //redis pub/sub
    }
}
