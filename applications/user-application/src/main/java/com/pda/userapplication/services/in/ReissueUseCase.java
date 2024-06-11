package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;

import java.util.Optional;

public interface ReissueUseCase {
    TokenInfoServiceResponse reissue(Optional<String> refreshToken);
}
