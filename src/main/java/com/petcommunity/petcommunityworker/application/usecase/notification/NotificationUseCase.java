package com.petcommunity.petcommunityworker.application.usecase.notification;


import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;

public interface NotificationUseCase {

    void send(EventMessage eventMessage);
}
