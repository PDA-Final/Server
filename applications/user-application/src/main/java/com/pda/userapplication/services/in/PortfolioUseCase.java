package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.PortfolioSubscribeServiceRequest;

public interface PortfolioUseCase {
    void subscribe(PortfolioSubscribeServiceRequest request);
}
