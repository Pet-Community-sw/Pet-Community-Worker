package com.petcommunity.application.in;


import com.petcommunity.infrastructure.mq.consumer.OutboxMessage;

public interface MemberSearchUseCase {
    void handle(OutboxMessage outboxMessage);
}
