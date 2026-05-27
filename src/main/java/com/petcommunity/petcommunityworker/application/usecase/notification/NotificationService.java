package com.petcommunity.petcommunityworker.application.usecase.notification;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.out.NotificationPort;
import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.application.usecase.notification.object.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements NotificationUseCase {

    private final NotificationPort notificationPort;
    private final JsonUtil jsonUtil;

    @Override
    public void send(EventMessage eventMessage) {
        NotificationDto notificationDto = jsonUtil.fromJson(eventMessage.getPayload(), NotificationDto.class);
        notificationPort.send("/sub/notification/" + notificationDto.getId(),
                new NotificationDto(notificationDto.getId(), notificationDto.getMessage()));
    }
}


