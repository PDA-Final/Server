package com.pda.userapplication.services.out.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowInfoResponse {
    private boolean isFollow;
    private long numOfFollowers;
    private long numOfFollowings;
}
