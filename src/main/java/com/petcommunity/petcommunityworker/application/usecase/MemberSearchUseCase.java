package com.petcommunity.petcommunityworker.application.usecase;


import com.petcommunity.petcommunityworker.infrastructure.mq.consumer.OutboxMessage;

public interface MemberSearchUseCase {
    void handle(OutboxMessage outboxMessage);
}
