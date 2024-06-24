package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.UserDetail;
import com.pda.userapplication.domains.vo.UserId;

import java.util.Optional;

public interface ReadUserDetailOutputPort {
    boolean existsByContact(String contact);
    Optional<UserDetail> findUserDetailById(UserId userId);
    UserDetail getAdmin();
}
