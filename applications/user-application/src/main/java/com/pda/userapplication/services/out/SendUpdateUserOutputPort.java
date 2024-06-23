package com.pda.userapplication.services.out;

import com.pda.userapplication.services.out.dto.req.UserUpdateOutputRequest;

public interface SendUpdateUserOutputPort {
    void sendUserOutput(UserUpdateOutputRequest request);
}
