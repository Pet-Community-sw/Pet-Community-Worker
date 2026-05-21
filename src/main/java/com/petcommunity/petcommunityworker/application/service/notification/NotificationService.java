package com.petcommunity.petcommunityworker.application.service.notification;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.in.chatting.NotificationDto;
import com.petcommunity.petcommunityworker.application.in.chatting.SendResponseDto;
import com.petcommunity.petcommunityworker.application.in.chatting.type.CommandType;
import com.petcommunity.petcommunityworker.application.in.notification.NotificationUseCase;
import com.petcommunity.petcommunityworker.application.in.notification.dto.NotificationEvent;
import com.petcommunity.petcommunityworker.application.in.notification.dto.NotificationListDto;
import com.petcommunity.petcommunityworker.application.out.SendPort;
import com.petcommunity.petcommunityworker.application.out.cache.AppOnlineCachePort;
import com.petcommunity.petcommunityworker.application.out.cache.NotificationsCachePort;
import com.petcommunity.petcommunityworker.infrastructure.mq.consumer.OutboxMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        NotificationEvent notificationEvent = jsonUtil.fromJson(outboxMessage.getPayload(), NotificationEvent.class);
        if (appOnlineCachePort.exist(notificationEvent.id())) {
            sendPort.send("/sub/notification/" + notificationEvent.id(),
                    SendResponseDto.builder().commandType(CommandType.NOTIFICATION).body(new NotificationDto(notificationEvent.id(), notificationEvent.message())).build());
        } else {
            log.info("backGroundMember");
//            fcmService.sendNotification(member.getFcmToken().getFcmToken(), "명냥로드", message);
        }
        notificationsCachePort.create(notificationEvent.id(), new NotificationListDto(notificationEvent.message(), LocalDateTime.now()), 3);
    }
}


