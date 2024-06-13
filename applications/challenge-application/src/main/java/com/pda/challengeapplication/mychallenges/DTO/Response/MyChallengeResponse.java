package com.pda.challengeapplication.mychallenges.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;


@Builder
@AllArgsConstructor
@Getter
public class MyChallengeResponse {
    Integer id;
    int challengeType;
    String name;
    int participants;
    String description;
    String logoUrl;
    String challengeUrl;
    String corpName;
    Timestamp endAt;
    //challenge 정보
    String status;
    Integer progress;

}
