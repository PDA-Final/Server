package com.pda.challengeapplication.mychallenges.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyChallengeRequest;
import com.pda.challengeapplication.mychallenges.dto.response.MyChallengeBadgeResponse;
import com.pda.challengeapplication.mychallenges.dto.response.MyChallengeResponse;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import com.pda.challengeapplication.mychallenges.service.MyChallengeService;
import com.pda.exceptionhandler.exceptions.ForbiddenException;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="MyChallenge", description = "투핀 챌린지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/my-challenges")
@Slf4j
public class MyChallengeController {
     private final MyChallengeService myChallengeService;


    //참여 챌린지 조회 (내 프로필 or 다른 사람 프로필 / 마감된(1) or 진행중인(0))
    @GetMapping
    @Operation(summary = "유저 참여 챌린지 조회", description = "유저 참여 챌린지를 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<MyChallengeResponse>> findChallengeByUserId(
            @RequestParam(value = "isDone") int isDone,
            @RequestParam (value = "userId") Long userId
    ) {

        List<MyChallengeResponse> myChallengeList;
        if(isDone == 0){
            myChallengeList = myChallengeService.findChallengeByUserId(userId);
        }else{
            myChallengeList = myChallengeService.findClosedChallengeByUserId(userId);
        }


        return ApiUtils.success("유저 참여 챌린지 조회", myChallengeList);
    }

    // 유저 챌린지 참여 여부 조회
    @GetMapping("/{id}")
    @Operation(summary = "유저 챌린지 참여 여부 조회", description = "유저가 챌린지에 참여했는지 조회합니다",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<Boolean> checkMyChallenge(
            @AuthUser AuthUserInfo userInfo,
            @PathVariable(value = "id") Integer id
    ) {
       boolean istrue= false;
       istrue = myChallengeService.checkMyChallenge(userInfo.getId(), id);


        return ApiUtils.success("유저 참여 챌린지 조회", istrue);
    }


     //기업 챌린지 결과 변경 (성공 or 실패)
    @PatchMapping("/{id}")
    @Operation (summary = "기업 챌린지 결과 변경", description = "기업 챌린지 결과를 변경합니다",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse changeChallengeStatus(
            @Valid @PathVariable(value = "id") Integer id,
            @RequestBody String status
    ){
        MyChallenge myChallenge = myChallengeService.changeChallengeStatus(id,status);
        return ApiUtils.success("변경된 기업 챌린지 결과");
    }

    // 챌린지 참여
    @PostMapping
    @Operation (summary = "자체 챌린지 참여", description = "자체 챌린지에 참여합니다(감정저축 챌린지는 따로 신청)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse participateChallenge(
            @RequestBody PostMyChallengeRequest postMyChallengeRequest,
             @AuthUser AuthUserInfo userInfo
    ){

        if(postMyChallengeRequest.getChallengeId() ==1){
            throw new ForbiddenException("감정저축챌린지는 다른 endpoint");
        }
        MyChallenge myChallenge = myChallengeService.participateChallenge(postMyChallengeRequest, userInfo.getId());
        String challengeName = myChallenge.getChallenge().getName();
        return ApiUtils.success("챌린지 참여",challengeName);

    }


    // 성공한 챌린지 뱃지 조회
    @GetMapping("/badge")
    @Operation (summary = "성공한 챌린지 뱃지 조회", description = "성공한 챌린지 뱃지들을 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<MyChallengeBadgeResponse>> readAllChallengeBadge(
            @RequestParam (value = "userId") Long userId
    ){
        List<MyChallengeBadgeResponse> badgeList = myChallengeService.readAllChallengeBadge(userId);
        return ApiUtils.success("달성 뱃지 조회", badgeList);
    }



}
