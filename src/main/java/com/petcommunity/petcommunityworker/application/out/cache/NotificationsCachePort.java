package com.petcommunity.petcommunityworker.application.out.cache;


import com.petcommunity.petcommunityworker.application.in.notification.dto.NotificationListDto;

public interface NotificationsCachePort {

    void create(Long id, NotificationListDto notificationListDto, int day);
}
