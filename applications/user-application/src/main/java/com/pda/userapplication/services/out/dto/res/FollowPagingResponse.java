package com.pda.userapplication.services.out.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FollowPagingResponse {
    private Long totalCount;
    private Long lastIndex;
    private boolean isLast;
    private List<FindFollowableUserResponse> users;
}
