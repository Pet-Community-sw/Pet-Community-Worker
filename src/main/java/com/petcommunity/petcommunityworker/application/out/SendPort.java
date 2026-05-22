package com.petcommunity.petcommunityworker.application.out;


import com.petcommunity.petcommunityworker.application.in.chatting.SendResponseDto;

public interface SendPort {
    void send(String destination, SendResponseDto<?> sendResponseDto);
}
