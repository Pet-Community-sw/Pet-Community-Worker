package com.petcommunity.petcommunityworker.application.out;


import com.petcommunity.petcommunityworker.application.usecase.notification.dto.NotificationDto;

public interface SendPort {
    void send(String destination, NotificationDto notificationDto);
}
