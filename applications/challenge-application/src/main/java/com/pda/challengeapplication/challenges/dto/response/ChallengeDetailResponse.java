package com.pda.challengeapplication.challenges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
public class ChallengeDetailResponse {
    long id;
    int challengeType;
    String name;
    String description;
    String logoUrl;
    LocalDate startAt;
    LocalDate endAt;
    int term;
    String detailDescription;
    String badgeName;
    Integer standardNum;
    String standardCg;
    Integer reward;
    int participation;

}
