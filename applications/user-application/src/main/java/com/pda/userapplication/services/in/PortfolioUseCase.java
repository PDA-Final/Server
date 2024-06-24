package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.PortfolioSubscribeServiceRequest;

public interface PortfolioUseCase {
    void getPortfolios(Long myId, Long toUserId);
    void subscribe(PortfolioSubscribeServiceRequest request);
}
