package com.petcommunity.petcommunityworker.infrastructure.mail;

import com.petcommunity.petcommunityworker.application.out.EmailPort;
import com.petcommunity.petcommunityworker.application.usecase.email.object.EmailEvent;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "mail.provider", havingValue = "smtp")
@RequiredArgsConstructor
public class SmtpAdapter implements EmailPort {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void send(EmailEvent emailEvent, String code) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setRecipients(Message.RecipientType.TO, emailEvent.getToEmail());
            message.setSubject(emailEvent.getSubject());
            message.setText(buildBody(code), "utf-8", "html");
            message.setFrom(new InternetAddress(email, "멍냥로드"));
            //InternetAddress: 이메일 주소를 RFC 표준 형식으로 감싸는 객체
            javaMailSender.send(message);
            log.info("메일 전송");
        } catch (Exception e) {
            log.error("메일 전송 실패. toEmail: {}", emailEvent.getToEmail(), e);
            throw new RuntimeException(e);
        }
    }

    private String buildBody(String code) {
        return "<div>" +
                "인증코드를 확인해주세요.<br><strong style=\"font-size: 30px;\">" +
                code +
                "</strong><br>인증코드는 3분간 유지됩니다.</div>";
    }
}
