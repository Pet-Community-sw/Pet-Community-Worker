package com.petcommunity.application.out;


import com.petcommunity.application.in.chatting.SendResponseDto;

public interface SendPort {
    void send(String destination, SendResponseDto<?> sendResponseDto);

    void sendToUser(String userId, String destination, SendResponseDto<?> sendResponseDto);
}
