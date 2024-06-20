package com.pda.creditapplication.service.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SetAmountServiceRequest {
    private Long userId;
    private Long amount;
    private LocalDateTime transactionDateTime;
}
