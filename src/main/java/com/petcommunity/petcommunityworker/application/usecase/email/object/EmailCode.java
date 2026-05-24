package com.petcommunity.petcommunityworker.application.usecase.email.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCode {
    private String code;
    private Long outboxId;
}
