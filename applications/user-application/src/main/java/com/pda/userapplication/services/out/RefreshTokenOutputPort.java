package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.User;

import java.util.Optional;

public interface RefreshTokenOutputPort {
    void save(User user, String refreshToken);
    Optional<User> findByRefreshTokenAndDelete(String refreshToken);
}
