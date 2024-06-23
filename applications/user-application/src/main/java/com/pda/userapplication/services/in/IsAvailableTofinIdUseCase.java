package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.res.AvailableTofinIdServiceResponse;

public interface IsAvailableTofinIdUseCase {
    AvailableTofinIdServiceResponse isAvailableTofinId(String tofinId);
}
