package com.petcommunity.petcommunityworker.infrastructure.mail;


import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;

public interface MailProvider {
    void send(EventMessage eventMessage);
}
