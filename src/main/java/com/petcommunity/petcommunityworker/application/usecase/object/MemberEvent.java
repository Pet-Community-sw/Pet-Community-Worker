package com.petcommunity.petcommunityworker.application.usecase.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEvent {

    private Long memberId;

    private String memberName;

    private String memberNameChosung;

    private String memberImageUrl;

    private MethodType methodType;
}
