package com.petcommunity.petcommunityworker.application.out.cache;


public interface NotificationsCachePort {

    void create(Long id, String message, int day);
}
