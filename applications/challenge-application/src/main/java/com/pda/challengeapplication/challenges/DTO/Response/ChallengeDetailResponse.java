package com.pda.challengeapplication.challenges.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Getter
public class ChallengeDetailResponse {
    Integer id;
    int challengeType;
    String name;
    int participants;
    String description;
    String logoUrl;
    Timestamp startAt;
    Timestamp endAt;
    String detailDescription;
}
