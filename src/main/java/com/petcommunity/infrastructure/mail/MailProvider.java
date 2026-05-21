package com.petcommunity.infrastructure.mail;


import com.petcommunity.infrastructure.mq.consumer.OutboxMessage;

public interface MailProvider {
    void send(OutboxMessage outboxMessage);
}
