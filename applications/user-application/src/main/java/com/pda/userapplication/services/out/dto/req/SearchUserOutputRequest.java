package com.pda.userapplication.services.out.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SearchUserOutputRequest {
    private String nickname;
    private Long limit;
    private Long lastIndex;
}
