package com.petcommunity.petcommunityworker.application.out.cache;


public interface NotificationCachePort {

    void create(Long id, String message, int day);
}
