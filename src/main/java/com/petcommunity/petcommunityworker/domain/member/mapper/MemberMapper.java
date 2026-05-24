package com.petcommunity.petcommunityworker.domain.member.mapper;


import com.petcommunity.petcommunityworker.application.usecase.member.object.MemberEvent;
import com.petcommunity.petcommunityworker.domain.member.model.MemberSearch;

public class MemberMapper {

    public static MemberSearch toSearchEntity(MemberEvent event, Long outboxId) {
        return MemberSearch.builder()
                .memberId(event.getMemberId())
                .memberName(event.getMemberName())
                .memberNameChosung(event.getMemberNameChosung())
                .memberImageUrl(event.getMemberImageUrl())
                .outboxEventId(outboxId)
                .build();
    }

    public static MemberSearch toDeleteSearchEntity(MemberEvent event, Long outboxId) {
        return MemberSearch.builder()
                .memberId(event.getMemberId())
                .outboxEventId(outboxId)
                .isDeleted(true)
                .build();
    }
}