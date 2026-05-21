package com.petcommunity.petcommunityworker.application.in.chatting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDto {
    private Long userId;
    private String message;
}
