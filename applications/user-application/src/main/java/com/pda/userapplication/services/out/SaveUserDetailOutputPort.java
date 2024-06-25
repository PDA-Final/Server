package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.UserDetail;

public interface SaveUserDetailOutputPort {
    UserDetail save(UserDetail userDetail);
}
