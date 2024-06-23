package com.pda.challengeapplication.mychallenges.dto.request.outer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Builder
public class TransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private long amount;
}
