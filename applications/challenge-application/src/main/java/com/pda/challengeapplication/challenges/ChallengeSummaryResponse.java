package com.pda.challengeapplication.challenges;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Getter
public class ChallengeSummaryResponse {
    int id;
    int challengeType;
    String name;
    int participants;
    String description;
    String logoUrl;
    Timestamp endAt;

}