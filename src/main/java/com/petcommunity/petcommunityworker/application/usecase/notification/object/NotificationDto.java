package com.petcommunity.petcommunityworker.application.usecase.notification.object;

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
