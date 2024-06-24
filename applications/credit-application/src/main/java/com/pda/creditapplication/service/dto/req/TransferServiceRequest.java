package com.pda.creditapplication.service.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TransferServiceRequest {
    private Long fromUserId;
    private Long toUserId;
    private Long amount;
    private LocalDateTime transactionDateTime;
}
