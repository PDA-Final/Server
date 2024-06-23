package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.NormalUser;

public interface SaveNormalUserOutputPort {
    NormalUser save(NormalUser normalUser);
}
