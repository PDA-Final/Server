package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SearchUserServiceRequest {
    private String nickname;
    private Long lastIndex;
    private Long limit;
}
