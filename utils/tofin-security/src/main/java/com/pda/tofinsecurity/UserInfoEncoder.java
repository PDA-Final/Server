package com.pda.tofinsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoEncoder {
    private final PasswordEncoder passwordEncoder;

    public String hashed(String userInfo) {
        return passwordEncoder.encode(userInfo);
    }

    public boolean matches(String userInfo, String hashedUserInfo) {
        return passwordEncoder.matches(userInfo, hashedUserInfo);
    }
}
