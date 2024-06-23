package com.pda.challengeapplication.mychallenges.dto.response.outer;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountResponse {
    private Long id;
    private String accountNumber;
    private String accountType;
    private String name;
    private String corpName;
    private String logo;
    private Long cash;
    private Double returnRate;
}
