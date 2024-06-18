package com.pda.challengeapplication.mychallenges.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyAccountRequest;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyEmoRequest;
import com.pda.challengeapplication.mychallenges.dto.response.MyEmoChallengeLogResponse;
import com.pda.challengeapplication.mychallenges.repository.MyAssetChallengeDetail;
import com.pda.challengeapplication.mychallenges.service.MyEmoChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    // 입출금 계좌 선택
    @PostMapping("/account")
    @Operation(summary = "입출금 계좌 선택", description = "입출금 계좌를 선택합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse<MyAssetChallengeDetail> createMyEmoAsset(
            @RequestBody PostMyAccountRequest postMyAccontRequest
    ){
        MyAssetChallengeDetail myAssetChallengeDetail = myEmoChallengeService.createMyEmoAsset(postMyAccontRequest);
        return ApiUtils.success("입출금 계좌 선택", myAssetChallengeDetail);
    }

    // 입출금 계좌 존재 확인
    @GetMapping("/account")
    @Operation(summary = "입출금 계좌 확인", description = "입출금 계좌를 확인합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<MyAssetChallengeDetail> checkMyEmoAsset(
            @RequestParam(value = "myChallengeId") long myChallengeId
    ){
        MyAssetChallengeDetail myAssetChallengeDetail = myEmoChallengeService.checkMyEmoAsset(myChallengeId);
        return ApiUtils.success("입출금 계좌 확인", myAssetChallengeDetail);
    }

    // 오늘 감정 기록
    @PostMapping("/emoji")
    @Operation(summary = "오늘 감정 저축", description = "오늘의 감정을 저축합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse createMyEmoLog(
            @RequestBody PostMyEmoRequest postMyEmoRequest
    ){
        myEmoChallengeService.createMyEmoLog(postMyEmoRequest);
        return ApiUtils.success("오늘 감정 기록 완료");
    }

    // 감정저축 로그 조회
    @GetMapping("/log")
    @Operation(summary = "감정 저축 기록 조회", description = "감정저축 챌린지 기록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<MyEmoChallengeLogResponse> readAllEmoChallengeLog(
            @RequestParam(value = "myChallengeId") long myChallengeId
    ){
       MyEmoChallengeLogResponse myEmoChallengeLogResponse = myEmoChallengeService.readAllEmoChallengeLog(myChallengeId);
        return ApiUtils.success("감정 저축 로그 조회", myEmoChallengeLogResponse);

    }


}
