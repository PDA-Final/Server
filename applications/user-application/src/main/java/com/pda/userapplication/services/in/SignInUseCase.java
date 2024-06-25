package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.SignInServiceRequest;
import com.pda.userapplication.services.in.dto.res.SignInResponse;

public interface SignInUseCase {
    SignInResponse signIn(SignInServiceRequest request);
}
