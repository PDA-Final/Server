package com.pda.userapplication.services.out;

import com.pda.userapplication.services.out.dto.req.TransferCreditRequest;

public interface CreditOutputPort {
    void consumeCredit(Long amount, String token);
    void transferCredit(TransferCreditRequest request);
}
