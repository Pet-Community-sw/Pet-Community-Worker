package com.petcommunity.petcommunityworker.infrastructure.stomp;

import com.petcommunity.petcommunityworker.application.usecase.notification.dto.NotificationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RedisNotificationMessage {
    String destination;
    NotificationDto notificationDto;
}
