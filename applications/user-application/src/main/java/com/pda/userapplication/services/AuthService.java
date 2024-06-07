package com.pda.userapplication.services;

import com.pda.userapplication.services.in.SignUpUseCase;
import com.pda.userapplication.services.in.dto.req.SignUpServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService implements SignUpUseCase {
    @Transactional
    @Override
    public void signUp(final SignUpServiceRequest request) {

    }
}
