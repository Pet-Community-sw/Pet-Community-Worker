package com.petcommunity.petcommunityworker.application.usecase.notification;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.out.NotificationPort;
import com.petcommunity.petcommunityworker.application.out.cache.AppOnlineCachePort;
import com.petcommunity.petcommunityworker.application.out.cache.NotificationCachePort;
import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import com.petcommunity.petcommunityworker.application.usecase.notification.object.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements NotificationUseCase {

    private final NotificationCachePort notificationCachePort;
    private final AppOnlineCachePort appOnlineCachePort;
    private final NotificationPort notificationPort;
    private final JsonUtil jsonUtil;
    //    private final FcmService fcmService;


    /*
     * foreground 유저는 sse, background 유저는 fcm --x
     * foreground 유저는 stomp, background 유저는 fcm
     * */
    @Override
    public void send(EventMessage eventMessage) {
        NotificationDto notificationDto = jsonUtil.fromJson(eventMessage.getPayload(), NotificationDto.class);
        if (appOnlineCachePort.exist(notificationDto.getId())) {
            notificationPort.send("/sub/notification/" + notificationDto.getId(),
                    new NotificationDto(notificationDto.getId(), notificationDto.getMessage()));
        } else {
            log.info("backGroundMember");
//            fcmService.sendNotification(member.getFcmToken().getFcmToken(), "명냥로드", message);
        }
        notificationCachePort.create(notificationDto.getId(), notificationDto.getMessage(), 3);
    }
}


