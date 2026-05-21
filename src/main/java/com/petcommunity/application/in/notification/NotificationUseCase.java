package com.petcommunity.application.in.notification;


import com.petcommunity.infrastructure.mq.consumer.OutboxMessage;

public interface NotificationUseCase {

    void send(OutboxMessage outboxMessage);
}
