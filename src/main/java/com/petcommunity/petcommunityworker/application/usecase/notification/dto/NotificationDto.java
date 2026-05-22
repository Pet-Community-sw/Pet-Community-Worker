package com.petcommunity.petcommunityworker.application.usecase.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDto {
    Long id;
    String message;
}
