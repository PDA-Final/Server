package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.User;

public interface CreateUserOutputPort {
    User create(User user);
}
