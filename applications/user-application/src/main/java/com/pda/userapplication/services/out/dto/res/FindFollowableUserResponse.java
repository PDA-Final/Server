package com.pda.userapplication.services.out.dto.res;

import com.pda.tofinenums.user.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FindFollowableUserResponse {
    private Long followId;
    private Long userId;
    private String nickname;
    private String profileImage;
    private String tofinId;
    private UserRole role;
    private boolean followStatus;
}
