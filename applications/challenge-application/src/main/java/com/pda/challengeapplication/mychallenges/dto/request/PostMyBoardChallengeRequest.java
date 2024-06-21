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
    @Schema(description ="챌린지 id", example = "1")
    long challengeId;

    @Schema(description ="유저 id", example = "1")
    long usesrId;

    @Schema(description = "나의 게시글 id ", example = "1")
    long boardId;

    public MyBoardChallenge convertToAccountEntity(MyChallenge mc) {
        MyBoardChallenge myBoardChallenge = new MyBoardChallenge(mc.getId(), boardId,mc);
        return myBoardChallenge;
    }
}
