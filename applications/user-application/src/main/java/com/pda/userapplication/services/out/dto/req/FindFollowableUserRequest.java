package com.pda.userapplication.services.out.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FindFollowableUserRequest {
    private Long toId;
    private Long fromId; // nullable
    private Long limit;
    private Long lastIndex; // nullable
}
