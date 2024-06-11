package com.pda.tofinsecurity.jwt;

import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.user.AuthUserInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class TokenableUser implements AuthUserInfo {
    private Long id;
    private UserRole userRole;
    private String profileImage;
    private String nickname;
    private LocalDate birth;
    private Job job;
}
