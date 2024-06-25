package com.pda.userapplication.services.out.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TransferCashRequest {
    private Long amount;
    private String frontId;
    private String backId;
    private String contact;
    private String fromAccountNumber;
    private String toAccountNumber;
}
