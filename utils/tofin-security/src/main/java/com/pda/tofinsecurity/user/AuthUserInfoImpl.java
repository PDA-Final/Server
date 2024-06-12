package com.pda.tofinsecurity.user;

import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AuthUserInfoImpl implements AuthUserInfo {
    private Long id;
    private String nickname;
    private String profileImage;
    private UserRole userRole;
    private LocalDate birth;
    private Job job;
    private String token;
}
