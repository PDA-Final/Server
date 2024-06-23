package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GetFollowableServiceRequest {
    private Long toId; // get followings method invoke can nullable
    private Long fromId; // get followers method invoke can nullable
    private Long limit;
    private Long lastIndex; // nullable
}
