package com.petcommunity.petcommunityworker.application.out.cache;

import com.petcommunity.petcommunityworker.application.usecase.email.EmailCode;

public interface EmailCachePort {
    void create(String key, EmailCode emailCode, long duration);

    EmailCode get(String key);
}
