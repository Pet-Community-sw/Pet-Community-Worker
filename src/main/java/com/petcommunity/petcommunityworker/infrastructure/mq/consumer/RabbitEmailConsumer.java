package com.petcommunity.petcommunityworker.infrastructure.mq.consumer;

import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.infrastructure.mail.MailProvider;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys;
import com.petcommunity.petcommunityworker.infrastructure.mq.RabbitRetryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitEmailConsumer {

    private final RabbitRetryHandler rabbitRetryHandler;
    private final MailProvider mailProvider;

    @RabbitListener(queues = RabbitKeys.MAIL_QUEUE)
    public void handle(EventMessage eventMessage, Message message) {//메시지 본문과 메타데이터를 받음
        try {
            mailProvider.send(eventMessage);
        } catch (Exception e) {
            rabbitRetryHandler.handle(eventMessage, message, e);
        }
    }
}
