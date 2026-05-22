package com.petcommunity.petcommunityworker.application.usecase.notification;


import com.petcommunity.petcommunityworker.infrastructure.mq.consumer.OutboxMessage;

public interface NotificationUseCase {

    void send(OutboxMessage outboxMessage);
}
