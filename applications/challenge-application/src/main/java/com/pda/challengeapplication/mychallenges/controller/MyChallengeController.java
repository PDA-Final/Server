package com.pda.challengeapplication.mychallenges.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.challengeapplication.mychallenges.DTO.Response.MyChallengeResponse;
import com.pda.challengeapplication.mychallenges.service.MyChallengeService;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name="MyChallenge", description = "투핀 유저 챌린지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/my-challenges")
@Slf4j
public class MyChallengeController {
     private final MyChallengeService myChallengeService;

    //참여 챌린지 조회 (내 프로필 or 다른 사람 프로필)
    @GetMapping
    @Operation(summary = "챌린지 조회", description = "유저 참여 챌린지를 조회합니다",
    security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<MyChallengeResponse>> findChallengeByUserId(
            @AuthUser AuthUserInfo userInfo,
            @RequestParam(value = "isDone") int isDone,
            @RequestParam (required = false, value = "userId") Long userId
    ) {
        if(userId == null){
            userId = userInfo.getId();
        }
        List<MyChallengeResponse> myChallengeList = new ArrayList<>();
        if(isDone == 0){
            myChallengeList = myChallengeService.findChallengeByUserId(userId);
        }else{
            myChallengeList = myChallengeService.findClosedChallengeByUserId(userId);
        }


        return ApiUtils.success("유저 참여 챌린지 조회", myChallengeList);
    }

}
