package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.application.usecase.notification.NotificationUseCase;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitRetryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitNotificationConsumer {

    private final NotificationUseCase useCase;
    private final RabbitRetryHandler handler;

    @RabbitListener(queues = RabbitKeys.NOTIFICATION_QUEUE)
    public void handle(EventMessage eventMessage, Message message) {
        try {
            useCase.send(eventMessage);
        } catch (Exception e) {
            handler.handle(eventMessage, message, e);
        }
    }
}
