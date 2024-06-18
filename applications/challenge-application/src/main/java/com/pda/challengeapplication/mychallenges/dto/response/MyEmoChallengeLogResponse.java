package com.pda.challengeapplication.mychallenges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyEmoChallengeLogResponse {
    List<MyEmoChallengeLog> myEmoChallengeLogs;
    int totalPrice;
}
