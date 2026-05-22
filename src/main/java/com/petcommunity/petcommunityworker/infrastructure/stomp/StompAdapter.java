package com.petcommunity.petcommunityworker.infrastructure.stomp;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.in.chatting.SendResponseDto;
import com.petcommunity.petcommunityworker.application.out.SendPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompAdapter implements SendPort {

    private final StringRedisTemplate template;
    private final JsonUtil jsonUtil;

    @Override
    public void send(String destination, SendResponseDto<?> sendResponseDto) {

        template.convertAndSend("notification-channel",
                jsonUtil.toJson(new RedisNotificationMessage(destination, sendResponseDto)));
        //redis pub/sub
    }
}
