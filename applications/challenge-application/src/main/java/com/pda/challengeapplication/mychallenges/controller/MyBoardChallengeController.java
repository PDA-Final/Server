package com.pda.challengeapplication.mychallenges.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyBoardChallengeRequest;
import com.pda.challengeapplication.mychallenges.service.MyBoardChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="MyBoardChallenge", description = "투핀 게시글 챌린지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/my-boardchallenges")
@Slf4j
public class MyBoardChallengeController {
    private final MyBoardChallengeService myBoardChallengeService;

     //챌린지 참여
    @PostMapping
    @Operation(summary = "게시글 ", description = "게시글 챌린지에 참여합니다",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse participateBoardChallenge(
            @RequestBody PostMyBoardChallengeRequest postMyBoardChallengeRequest
            ){
       myBoardChallengeService.writeBoardChallenge(postMyBoardChallengeRequest);
        return ApiUtils.success("게시글 작성 완료");

    }


}
