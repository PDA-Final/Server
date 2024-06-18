package com.pda.challengeapplication.mychallenges.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyChallengeRequest {

    long id;
    long userId;
    long challengeId;
    LocalDate startAt;
    LocalDate endAt;
    String status;

}
