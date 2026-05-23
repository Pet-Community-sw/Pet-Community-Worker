package com.petcommunity.petcommunityworker.application.out;


import com.petcommunity.petcommunityworker.application.usecase.email.EmailEvent;

public interface EmailPort {
    void send(EmailEvent emailEvent, String code);
}
