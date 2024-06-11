package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.SignUpServiceRequest;

public interface SignUpUseCase {
    void signUp(SignUpServiceRequest request);
}
