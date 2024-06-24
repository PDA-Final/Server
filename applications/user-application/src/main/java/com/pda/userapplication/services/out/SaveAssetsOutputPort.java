package com.pda.userapplication.services.out;

import com.pda.userapplication.services.out.dto.req.TransferCashRequest;

public interface SaveAssetsOutputPort {
    void transfer(TransferCashRequest request);
}
