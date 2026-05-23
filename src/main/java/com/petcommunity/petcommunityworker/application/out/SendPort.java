package com.petcommunity.petcommunityworker.application.out;


import com.petcommunity.petcommunityworker.application.usecase.notification.object.NotificationDto;

public interface SendPort {
    void send(String destination, NotificationDto notificationDto);
}
