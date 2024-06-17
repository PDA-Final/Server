package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.NormalUser;

public interface SaveUserDetailOutputPort {
    NormalUser save(NormalUser normalUser);
}
