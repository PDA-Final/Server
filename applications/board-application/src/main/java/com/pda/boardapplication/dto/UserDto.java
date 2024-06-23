package com.pda.boardapplication.dto;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.user.AuthUserInfo;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @Builder
    public static class InfoDto {
        private long id;
        private String nickname;
        private String profile;
        private UserRole type;

        public static InfoDto fromAuthUserInfo(AuthUserInfo authUserInfo) {
            return builder()
                    .id(authUserInfo.getId())
                    .nickname(authUserInfo.getNickname())
                    .profile(authUserInfo.getProfileImage())
                    .type(authUserInfo.getUserRole())
                    .build();
        }
    }
}
