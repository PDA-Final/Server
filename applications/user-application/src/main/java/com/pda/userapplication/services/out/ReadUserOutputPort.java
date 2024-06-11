package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.TofinId;

import java.util.Optional;

public interface ReadUserOutputPort {
    boolean isExistsByTofinId(TofinId tofinId);
    User getByTofinId(TofinId tofinId);
    Optional<User> findByTofinId(TofinId tofinId);
}
