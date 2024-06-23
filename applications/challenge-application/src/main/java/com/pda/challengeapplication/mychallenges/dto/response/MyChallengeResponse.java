package com.pda.challengeapplication.mychallenges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@Builder
public class MyChallengeResponse {
    long myChallengeId;
    long challengeId;
    int challengeType;
    String name;
    int participants;
    String description;
    String logoUrl;
    String challengeUrl;
    String corpName;
    LocalDate endAt;
    //challenge 정보
    String status;
    Integer progress;
    Integer term;

}

