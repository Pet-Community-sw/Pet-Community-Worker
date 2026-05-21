package com.petcommunity.petcommunityworker.application.in.notification;


import com.petcommunity.petcommunityworker.infrastructure.mq.consumer.OutboxMessage;

public interface NotificationUseCase {

    void send(OutboxMessage outboxMessage);
}
