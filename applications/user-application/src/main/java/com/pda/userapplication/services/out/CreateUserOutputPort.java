package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.NormalUser;

public interface CreateUserOutputPort {
    NormalUser create(NormalUser user);
}
