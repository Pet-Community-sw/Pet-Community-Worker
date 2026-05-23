package com.petcommunity.petcommunityworker.application.usecase.member;


import com.petcommunity.petcommunityworker.application.usecase.message.EventMessage;

public interface MemberSearchUseCase {
    void handle(EventMessage eventMessage);
}
