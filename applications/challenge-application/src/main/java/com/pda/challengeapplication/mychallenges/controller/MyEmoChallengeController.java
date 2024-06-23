package com.pda.challengeapplication.mychallenges.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.challengeapplication.mychallenges.dto.request.outer.PostMyEmoChallengeRequest;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyEmoLogRequest;
import com.pda.challengeapplication.mychallenges.dto.response.MyEmoChallengeLogResponse;
import com.pda.challengeapplication.mychallenges.service.MyEmoChallengeService;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name="MyEmoChallenge", description = "투핀 감정 저축 챌린지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/my-emoChallenges")
@Slf4j
public class MyEmoChallengeController {
    private final MyEmoChallengeService myEmoChallengeService;

    // 감정 챌린지 참여
    @PostMapping
    @Operation(summary = "감정 챌린지 참여", description = "감정 챌린지에 참여합니다",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse participateEmoChallenge(
            @AuthUser AuthUserInfo userInfo,
            @RequestBody PostMyEmoChallengeRequest pa
    ){

        myEmoChallengeService.participateEmoChallenge(pa, userInfo.getToken(), userInfo.getId());
        return ApiUtils.success("챌린지 참여");

    }

    // 오늘 감정 기록
    @PostMapping("/emoji")
    @Operation(summary = "오늘 감정 저축", description = "오늘의 감정을 저축합니다",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse createMyEmoLog(
            @AuthUser AuthUserInfo userInfo,
            @RequestBody PostMyEmoLogRequest postMyEmoRequest
    ){
        myEmoChallengeService.createMyEmoLog(postMyEmoRequest, userInfo.getToken(), userInfo.getId());
        return ApiUtils.success("오늘 감정 기록 완료");
    }

    // 감정저축 로그 조회
    @GetMapping("/log")
    @Operation(summary = "감정 저축 기록 조회", description = "감정저축 챌린지 기록을 조회합니다",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<MyEmoChallengeLogResponse> readAllEmoChallengeLog(
            @AuthUser AuthUserInfo userInfo
    ){
       MyEmoChallengeLogResponse myEmoChallengeLogResponse = myEmoChallengeService.readAllEmoChallengeLog(userInfo.getId());
        return ApiUtils.success("감정 저축 로그 조회", myEmoChallengeLogResponse);

    }


}
