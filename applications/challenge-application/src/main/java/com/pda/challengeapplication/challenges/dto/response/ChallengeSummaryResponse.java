package com.pda.challengeapplication.challenges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter

public class ChallengeSummaryResponse {
    long id;
    int challengeType;
    String name;
    String description;
    String logoUrl;
    LocalDate endAt;
    String challengeUrl;
    String corpName;
    Integer term;
    Integer reward;
    int participation;

}