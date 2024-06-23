package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.SetTendencyServiceRequest;

public interface SetTendencyUseCase {
    void setTendency(SetTendencyServiceRequest request);
}
