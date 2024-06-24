package com.pda.challengeapplication.mychallenges.dto.request.outer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SendChallengeResultRequest {
    private Long userId;
    private String logoUrl;
    private String challengeName;
    private String result;
    private LocalDateTime transactionDateTime;
}
