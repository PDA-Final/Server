package com.pda.userapplication.outadapters.jpa;

import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.services.out.CreateUserOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import org.springframework.stereotype.Service;

@Service
public class UserJpaAdapter implements CreateUserOutputPort, ReadUserOutputPort {
    @Override
    public NormalUser create(NormalUser user) {
        return null;
    }

    @Override
    public boolean isExistsByTofinId(String tofinId) {
        return false;
    }
}
