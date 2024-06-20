package com.pda.challengeapplication.mychallenges.dto.request;

import com.pda.challengeapplication.mychallenges.repository.MyBoardChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyBoardChallengeRequest {
    @Schema(description = "나의 챌린지 id (챌린지 id랑 다름!!)", example = "1")
    long myChallengeId;
    @Schema(description = "나의 게시글 id ", example = "1")
    long boardId;

    public MyBoardChallenge convertToAccountEntity(MyChallenge mc) {
        MyBoardChallenge myBoardChallenge = new MyBoardChallenge(myChallengeId, boardId,mc);
        return myBoardChallenge;
    }
}
