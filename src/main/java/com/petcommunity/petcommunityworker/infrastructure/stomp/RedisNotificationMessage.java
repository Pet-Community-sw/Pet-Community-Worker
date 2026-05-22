package com.petcommunity.petcommunityworker.infrastructure.stomp;

import com.petcommunity.petcommunityworker.application.in.chatting.SendResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RedisNotificationMessage {
    String destination;
    SendResponseDto<?> payload;
}
