package com.pda.challengeapplication.mychallenges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class MyEmoChallengeLog {
    LocalDate savingAt;
    String emojiUrl;
    String emotion;
    int price;
}
