package com.pda.userapplication.services.auth.in;

import com.pda.userapplication.services.auth.in.dto.req.SignUpServiceRequest;

public interface SignUpUseCase {
    void signUp(SignUpServiceRequest request);
}
