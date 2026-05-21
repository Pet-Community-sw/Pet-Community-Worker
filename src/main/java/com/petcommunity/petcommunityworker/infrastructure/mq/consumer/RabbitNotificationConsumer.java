package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.in.notification.NotificationUseCase;
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
    public void handle(OutboxMessage outboxMessage, Message message) {
        try {
            useCase.send(outboxMessage);
        } catch (Exception e) {
            handler.handle(outboxMessage, message, e);
        }
    }
}
