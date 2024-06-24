package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.UpdateProfileServiceRequest;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;

public interface UpdateUserUseCase {
    TokenInfoServiceResponse updateProfile(UpdateProfileServiceRequest request);
}
