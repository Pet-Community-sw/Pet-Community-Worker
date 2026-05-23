package com.petcommunity.petcommunityworker.application.usecase.email;

import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;

public interface EmailUseCase {

    void send(EventMessage eventMessage);
}
