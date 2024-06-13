package com.pda.tofinsecurity.user;

import com.pda.tofinsecurity.jwt.TokenableInfo;

public interface AuthUserInfo extends TokenableInfo {
    String getToken();
}
