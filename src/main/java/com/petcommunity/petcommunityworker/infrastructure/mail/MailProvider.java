package com.petcommunity.petcommunityworker.infrastructure.mail;


import com.petcommunity.petcommunityworker.infrastructure.mq.consumer.OutboxMessage;

public interface MailProvider {
    void send(OutboxMessage outboxMessage);
}
