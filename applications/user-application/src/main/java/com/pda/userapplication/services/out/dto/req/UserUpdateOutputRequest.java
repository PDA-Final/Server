package com.pda.userapplication.services.out.dto.req;

import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateOutputRequest {
    private Long userId;
    private String nickname;
    private String profileImage;
    private Job job;
    private UserRole role;
}
