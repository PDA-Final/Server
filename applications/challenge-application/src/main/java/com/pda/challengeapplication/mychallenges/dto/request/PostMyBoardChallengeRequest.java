package com.pda.challengeapplication.mychallenges.dto.request;

import com.pda.challengeapplication.mychallenges.repository.MyBoardChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyBoardChallengeRequest {
    @Id
    long myChallengeId;
    long boardId;

    public MyBoardChallenge convertToAccountEntity(MyChallenge mc) {
        MyBoardChallenge myBoardChallenge = new MyBoardChallenge(myChallengeId, boardId,mc);
        return myBoardChallenge;
    }
}
