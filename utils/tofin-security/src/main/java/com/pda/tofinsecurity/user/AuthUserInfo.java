package com.pda.tofinsecurity.user;

import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;

import java.time.LocalDate;

public interface AuthUserInfo {
    Long getId();
    UserRole getUserRole();
    String getProfileImage();
    String getNickname();
    LocalDate getBirth();
    Job getJob();
}
