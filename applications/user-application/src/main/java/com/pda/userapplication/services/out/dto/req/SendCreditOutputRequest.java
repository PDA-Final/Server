package com.pda.userapplication.services.out.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SendCreditOutputRequest {
    private Long userId;
    private Long amount;
    private LocalDateTime transactionDateTime;
}
