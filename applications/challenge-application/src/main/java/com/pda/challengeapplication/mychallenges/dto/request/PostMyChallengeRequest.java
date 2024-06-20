package com.pda.challengeapplication.mychallenges.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyChallengeRequest {

    long id;
    @Schema(description = "챌린지 id)", example = "1")
    long challengeId;

}
