package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.usecase.email.EmailUseCase;
import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitEmailConsumer extends RabbitAbstractConsumer {

    private final EmailUseCase useCase;

    @RabbitListener(queues = RabbitKeys.MAIL_QUEUE)
    public void listener(EventMessage eventMessage, Message message) {//메시지 본문과 메타데이터를 받음
        consume(eventMessage, message);
    }

    @Override
    protected void handle(EventMessage eventMessage) {
        useCase.send(eventMessage);
    }
}
