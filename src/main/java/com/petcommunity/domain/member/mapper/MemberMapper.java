package com.petcommunity.domain.member.mapper;


import com.petcommunity.application.in.object.MemberEvent;
import com.petcommunity.domain.member.model.MemberSearch;

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