package com.petcommunity.application.in.chatting;

import com.petcommunity.application.in.chatting.type.CommandType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendResponseDto<T> {
    private CommandType commandType;
    private T body;
}
