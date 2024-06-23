package com.pda.challengeapplication.mychallenges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MyChallengeBadgeResponse {
    String imgUrl;
    String badgeName;
    String challengeName;
}
