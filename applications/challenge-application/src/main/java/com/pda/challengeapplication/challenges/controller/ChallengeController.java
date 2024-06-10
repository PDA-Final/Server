package com.pda.challengeapplication.challenges.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.challengeapplication.challenges.ChallengeDTO;
import com.pda.challengeapplication.challenges.ChallengeSummaryResponse;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Challenge", description = "투핀 챌린지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/challenges")
@Slf4j
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    @Operation(summary = "챌린지 등록", description = "기업 및 자체 챌린지를 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse registerChallenge(@Valid @RequestBody ChallengeDTO challengeDTO){
        log.info("challengeDTO",challengeDTO);
        Challenge savedChallenge = challengeService.createChallenge(challengeDTO);
        return ApiUtils.success("챌린지 등록", savedChallenge);
    }

    @GetMapping
    public GlobalResponse<List<ChallengeSummaryResponse>> findAllChallenge(
           @RequestParam(required = false, value = "sortId") String sortId)
    {

        if(sortId == "New") {
            List<ChallengeSummaryResponse> challenges = challengeService.readAllChallengeByNew(sortId);
            return ApiUtils.success("챌린지 조회", challenges);
        }else{
            List<ChallengeSummaryResponse> challenges = challengeService.readAllChallengeByEnd(sortId);
            return ApiUtils.success("챌린지 조회", challenges);
        }

    }

}
