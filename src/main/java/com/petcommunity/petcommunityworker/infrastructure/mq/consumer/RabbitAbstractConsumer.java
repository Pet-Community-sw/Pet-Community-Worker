package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitRetryHandler;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class RabbitAbstractConsumer {

    @Autowired
    private RabbitRetryHandler rabbitRetryHandler;

    protected void consume(EventMessage eventMessage, Message message) {
        try {
            handle(eventMessage);
        } catch (Exception e) {
            rabbitRetryHandler.handle(eventMessage, message, e);
        }
    }

    protected abstract void handle(EventMessage eventMessage);
}
