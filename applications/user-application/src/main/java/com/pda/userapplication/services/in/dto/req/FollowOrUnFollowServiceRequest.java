package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowOrUnFollowServiceRequest {
    private Long fromId;
    private Long toId;
}
