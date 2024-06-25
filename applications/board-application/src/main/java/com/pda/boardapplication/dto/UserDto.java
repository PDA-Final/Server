package com.pda.boardapplication.dto;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.user.AuthUserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

public class UserDto {

    @Getter
    @Builder
    public static class InfoDto {
        private long id;
        private String nickname;
        private String profile;
        private UserRole type;
        private String token;

        public static InfoDto fromAuthUserInfo(AuthUserInfo authUserInfo) {
            return builder()
                    .id(authUserInfo.getId())
                    .nickname(authUserInfo.getNickname())
                    .profile(authUserInfo.getProfileImage())
                    .type(authUserInfo.getUserRole())
                    .token(authUserInfo.getToken())
                    .build();
        }
    }
}
