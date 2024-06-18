package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.SignUpServiceRequest;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;

public interface SignUpUseCase {
    TokenInfoServiceResponse signUp(SignUpServiceRequest request);
}
