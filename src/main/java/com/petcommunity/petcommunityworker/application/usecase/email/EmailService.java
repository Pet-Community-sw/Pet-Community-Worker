package com.petcommunity.petcommunityworker.application.usecase.email;

import com.petcommunity.petcommunityworker.application.common.JsonUtil;
import com.petcommunity.petcommunityworker.application.out.EmailPort;
import com.petcommunity.petcommunityworker.application.out.cache.EmailCachePort;
import com.petcommunity.petcommunityworker.application.usecase.email.object.EmailCode;
import com.petcommunity.petcommunityworker.application.usecase.email.object.EmailEvent;
import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailUseCase {

    private final EmailCachePort emailCachePort;
    private final EmailPort emailPort;
    private final JsonUtil jsonUtil;

    @Override
    public void send(EventMessage eventMessage) {
        EmailEvent emailEvent = jsonUtil.fromJson(eventMessage.getPayload(), EmailEvent.class);
        EmailCode emailCode = emailCachePort.get(emailEvent.getToEmail());
        if (emailCode != null && emailCode.getOutboxId() >= eventMessage.getId()) return; // 이미 발송된 코드가 존재하면 재발송하지 않음

        String code = buildCode();

        emailPort.send(emailEvent, code);
        emailCachePort.createAuthCode(emailEvent.getToEmail(), new EmailCode(code, eventMessage.getId()), 3); // ttl 설정
    }

    private String buildCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int n = random.nextInt(10);
            sb.append(n);
        }
        return sb.toString();
    }
}
