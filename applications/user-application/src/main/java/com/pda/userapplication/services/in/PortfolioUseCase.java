package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.PortfolioSubscribeServiceRequest;
import com.pda.userapplication.services.in.dto.res.GetPortfolioServiceResponse;

public interface PortfolioUseCase {
    GetPortfolioServiceResponse getPortfolios(Long myId, Long toUserId);
    void subscribe(PortfolioSubscribeServiceRequest request);
}
