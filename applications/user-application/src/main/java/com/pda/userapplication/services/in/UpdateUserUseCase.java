package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.UpdateProfileServiceRequest;

public interface UpdateUserUseCase {
    void updateProfile(UpdateProfileServiceRequest request);
}
