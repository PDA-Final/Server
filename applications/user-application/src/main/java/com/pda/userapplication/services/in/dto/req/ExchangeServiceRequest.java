package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExchangeServiceRequest {
    private Long userId;
    private String token;
    private Long amount;
    private String accountNumber;
}
