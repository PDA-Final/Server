package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.TofinId;
import com.pda.userapplication.domains.vo.UserId;

import java.util.Optional;

public interface ReadUserOutputPort {
    boolean isExistsByTofinId(TofinId tofinId);
    User getByTofinId(TofinId tofinId);
    Optional<User> findByTofinId(TofinId tofinId);
    Optional<User> findById(UserId userId);
    User getByUserId(UserId userId);
}
