package com.pda.userapplication.services.auth;

import com.pda.userapplication.services.auth.in.SignUpUseCase;
import com.pda.userapplication.services.auth.in.dto.req.SignUpServiceRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements SignUpUseCase {
    @Override
    public void signUp(SignUpServiceRequest request) {

    }
}
