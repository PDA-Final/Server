package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.User;

public interface SaveUserOutputPort {
    User save(User user);
}
