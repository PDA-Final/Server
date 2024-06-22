package com.pda.userapplication.services.out.dto.res;

import com.pda.userapplication.domains.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SearchUserPagingOutputResponse {
    private Long totalCount;
    private Long lastIndex;
    private boolean isLast;
    private List<User> users;
}
