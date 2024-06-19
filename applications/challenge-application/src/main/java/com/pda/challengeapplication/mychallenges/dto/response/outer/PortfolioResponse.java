package com.pda.challengeapplication.mychallenges.dto.response.outer;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PortfolioResponse {
    private String code;
    private String stockType;
    private String name;
    private String engName;
    private int price;
    private Double dollar;
    private int quantity;
}
