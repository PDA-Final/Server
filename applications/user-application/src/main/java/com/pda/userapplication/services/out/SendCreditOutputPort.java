package com.pda.userapplication.services.out;

import com.pda.userapplication.services.out.dto.req.SendCreditOutputRequest;

public interface SendCreditOutputPort {
    void addCredit(SendCreditOutputRequest request);
}
