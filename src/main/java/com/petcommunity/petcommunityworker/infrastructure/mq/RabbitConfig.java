package com.petcommunity.petcommunityworker.infrastructure.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.petcommunity.petcommunityworker.infrastructure.mq.RabbitKeys.*;


@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class RabbitConfig {

    //Exchange
    @Bean
    DirectExchange mainExchange() {
        return new DirectExchange(MAIN_EXCHANGE);
    }

    @Bean
    HeadersExchange retryExchange() {
        return new HeadersExchange(RETRY_EXCHANGE);
    }

    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    //Queue
    @Bean
    public Queue mailQueue() {
        return QueueBuilder.durable(MAIL_QUEUE).build();
    }

    @Bean
    public Queue notificationQueue() {
        return QueueBuilder.durable(NOTIFICATION_QUEUE).build();
    }

    @Bean
    public Queue elasticQueue() {
        return QueueBuilder.durable(MEMBER_QUEUE).build();
    }

    @Bean
    public Queue retry5sQueue() {
        return QueueBuilder.durable(RETRY_5S_QUEUE)
                .deadLetterExchange(MAIN_EXCHANGE)
                .ttl(5000)
                .build();
    }

    @Bean
    public Queue retry30sQueue() {
        return QueueBuilder.durable(RETRY_30S_QUEUE)
                .deadLetterExchange(MAIN_EXCHANGE)
                .ttl(30000)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    //Binding
    @Bean
    public Binding mailBinding(Queue mailQueue, DirectExchange mainExchange) {
        return BindingBuilder.bind(mailQueue).to(mainExchange).with(MAIL_ROUTING_KEY);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange mainExchange) {
        return BindingBuilder.bind(notificationQueue).to(mainExchange).with(NOTIFICATION_ROUTING_KEY);
    }

    //ES 바인딩
    @Bean
    public Binding elasticBinding(Queue elasticQueue, DirectExchange mainExchange) {
        return BindingBuilder.bind(elasticQueue).to(mainExchange).with(MEMBER_ROUTING_KEY);
    }

    @Bean
    public Binding retry5sBinding(Queue retry5sQueue, HeadersExchange retryExchange) {
        return BindingBuilder.bind(retry5sQueue).to(retryExchange).where("retry-type").matches(RETRY_5S_ROUTING_KEY);
    }

    @Bean
    public Binding retry30sBinding(Queue retry30sQueue, HeadersExchange retryExchange) {
        return BindingBuilder.bind(retry30sQueue).to(retryExchange).where("retry-type").matches(RETRY_30S_ROUTING_KEY);
    }

    @Bean
    public Binding dlqBinding(Queue deadLetterQueue, DirectExchange dlxExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(dlxExchange).with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
