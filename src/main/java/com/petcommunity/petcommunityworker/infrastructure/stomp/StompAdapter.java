package com.petcommunity.petcommunityworker.infrastructure.stomp;

import com.petcommunity.petcommunityworker.application.in.chatting.SendResponseDto;
import com.petcommunity.petcommunityworker.application.out.SendPort;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompAdapter implements SendPort {

    private final SimpMessagingTemplate template;

    @Override
    public void send(String destination, SendResponseDto<?> sendResponseDto) {
        template.convertAndSend(destination, sendResponseDto);
    }

    @Override
    public void sendToUser(String userId, String destination, SendResponseDto<?> sendResponseDto) {
        template.convertAndSendToUser(userId, destination, sendResponseDto);
    }
}
