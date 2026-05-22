package com.petcommunity.petcommunityworker.application.service.notification;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.out.SendPort;
import com.petcommunity.petcommunityworker.application.out.cache.AppOnlineCachePort;
import com.petcommunity.petcommunityworker.application.out.cache.NotificationsCachePort;
import com.petcommunity.petcommunityworker.application.usecase.notification.NotificationUseCase;
import com.petcommunity.petcommunityworker.application.usecase.notification.dto.NotificationDto;
import com.petcommunity.petcommunityworker.infrastructure.mq.consumer.OutboxMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements NotificationUseCase {

    private final NotificationsCachePort notificationsCachePort;
    private final AppOnlineCachePort appOnlineCachePort;
    private final SendPort sendPort;
    private final JsonUtil jsonUtil;
    //    private final FcmService fcmService;


    /*
     * foreground 유저는 sse, background 유저는 fcm --x
     * foreground 유저는 stomp, background 유저는 fcm
     * */
    @Override
    public void send(OutboxMessage outboxMessage) {
        NotificationDto notificationDto = jsonUtil.fromJson(outboxMessage.getPayload(), NotificationDto.class);
        if (appOnlineCachePort.exist(notificationDto.getId())) {
            sendPort.send("/sub/notification/" + notificationDto.getId(),
                    new NotificationDto(notificationDto.getId(), notificationDto.getMessage()));
        } else {
            log.info("backGroundMember");
//            fcmService.sendNotification(member.getFcmToken().getFcmToken(), "명냥로드", message);
        }
        notificationsCachePort.create(notificationDto.getId(), notificationDto.getMessage(), 3);
    }
}


