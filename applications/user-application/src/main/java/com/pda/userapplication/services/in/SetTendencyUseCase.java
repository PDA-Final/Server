package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.SetTendencyServiceRequest;
import com.pda.userapplication.services.in.dto.res.SetTendencyResponse;

public interface SetTendencyUseCase {
    SetTendencyResponse setTendency(SetTendencyServiceRequest request);
}
