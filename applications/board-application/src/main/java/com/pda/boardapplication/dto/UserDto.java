package com.pda.boardapplication.dto;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.jwt.TokenableUser;
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

        public static InfoDto fromTokenableUser(TokenableUser tokenableUser) {
            return builder()
                    .id(tokenableUser.getId())
                    .nickname(tokenableUser.getNickname())
                    .profile(tokenableUser.getProfileImage())
                    .type(tokenableUser.getUserRole())
                    .build();
        }
    }
}
