package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.domains.vo.UserId;

import java.util.Optional;

public interface ReadNormalUserOutputPort {
    boolean existsByContact(String contact);
    Optional<NormalUser> findByUserId(UserId userId);
}
