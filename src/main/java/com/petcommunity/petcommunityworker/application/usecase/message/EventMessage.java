package com.petcommunity.petcommunityworker.application.usecase.message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class EventMessage {

    private Long id;

    private String payload;
}