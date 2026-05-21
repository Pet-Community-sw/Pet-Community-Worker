package com.petcommunity.application.out.cache;


import com.petcommunity.application.in.notification.dto.NotificationListDto;

public interface NotificationsCachePort {

    void create(Long id, NotificationListDto notificationListDto, int day);
}
