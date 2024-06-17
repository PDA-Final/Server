package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.User;

import java.util.Optional;

public interface RefreshTokenOutputPort {
    void saveOnlyOneUser(User user, String refreshToken);
    Optional<User> deleteByRefreshToken(String refreshToken);
}
