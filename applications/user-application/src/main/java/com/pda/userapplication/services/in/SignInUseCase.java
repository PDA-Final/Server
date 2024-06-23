package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.SignInServiceRequest;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;

public interface SignInUseCase {
    TokenInfoServiceResponse signIn(SignInServiceRequest request);
}
