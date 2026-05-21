package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.in.MemberSearchUseCase;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitRetryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMemberConsumer {

    private final MemberSearchUseCase useCase;
    private final RabbitRetryHandler rabbitRetryHandler;

    @RabbitListener(queues = RabbitKeys.MEMBER_QUEUE)
    public void hande(OutboxMessage outboxMessage, Message message) {
        try {
            useCase.handle(outboxMessage);
        } catch (Exception e) {
            rabbitRetryHandler.handle(outboxMessage, message, e);
        }
    }
}
