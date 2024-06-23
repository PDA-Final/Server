package com.pda.challengeapplication.mychallenges.dto.response.outer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetInfoResponse {
    private List<AccountResponse> accounts;
    private Double portfolioReturnRate;
    private List<PortfolioResponse> portfolio;
    private List<CardResponse> cards;
    private List<FundResponse> funds;
    private List<LoanResponse> loans;
}
