package com.pda.challengeapplication.mychallenges.dto.response.outer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserAccount {
    private Long id;
    private String accountNumber;
    private String accountType;
    private String name;
    private String corpName;
    private String logo;
    private Long cash;
    private Double returnRate;
}
