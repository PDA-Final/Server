package com.pda.userapplication.services.out.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SendMessageRequest {
    private Long userId;
    private Long targetId;
    private String message;
    private String messageType;
    private String image;
}
