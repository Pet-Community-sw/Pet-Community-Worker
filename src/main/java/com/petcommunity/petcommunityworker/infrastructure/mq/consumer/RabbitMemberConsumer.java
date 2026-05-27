package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.usecase.member.MemberSearchUseCase;
import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMemberConsumer extends RabbitAbstractConsumer {

    private final MemberSearchUseCase useCase;

    @RabbitListener(queues = RabbitKeys.MEMBER_QUEUE)
    public void listener(EventMessage eventMessage, Message message) {
        consume(eventMessage, message);
    }

    @Override
    protected void handle(EventMessage eventMessage) {
        useCase.handle(eventMessage);
    }
}
