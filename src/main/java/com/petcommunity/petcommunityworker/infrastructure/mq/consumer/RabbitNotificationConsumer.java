package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.application.usecase.notification.NotificationUseCase;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitNotificationConsumer extends RabbitAbstractConsumer {
    private final NotificationUseCase useCase;

    @RabbitListener(queues = RabbitKeys.NOTIFICATION_QUEUE)
    public void listener(EventMessage eventMessage, Message message) {
        consume(eventMessage, message);
    }

    @Override
    protected void handle(EventMessage eventMessage) {
        useCase.send(eventMessage);
    }
}
