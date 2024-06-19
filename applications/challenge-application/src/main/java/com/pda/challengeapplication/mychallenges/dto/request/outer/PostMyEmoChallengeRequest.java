package com.pda.challengeapplication.mychallenges.dto.request.outer;

import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyEmoChallengeRequest {

    long id;
    long challengeId;
    String inACNT;
    String outACNT;

    public MyChallenge converToMCEntity(long id, Challenge c, long userid, LocalDate startAt, LocalDate endAt, String status){
        return new MyChallenge(id, c, userid, startAt, endAt, status );
    }
}
