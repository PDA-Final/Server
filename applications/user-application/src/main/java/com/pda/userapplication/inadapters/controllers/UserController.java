package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.userapplication.services.in.GetJobsUseCase;
import com.pda.userapplication.services.in.IsAvailableTofinIdUseCase;
import com.pda.userapplication.services.in.dto.res.AvailableTofinIdServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "투핀 유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final GetJobsUseCase getJobsUseCase;
    private final IsAvailableTofinIdUseCase isAvailableTofinIdUseCase;

    @GetMapping("/jobs")
    @Operation(summary = "직업 리스트 조회", description = "모든 직업들의 리스트를 한글로 반환합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<String>> getJobs() {
        return ApiUtils.success(
            "직업 리스트 조회", getJobsUseCase.getJobs());
    }

    @GetMapping("/available-id")
    @Operation(summary = "사용가능한 아이디 조회", description = "사용가능한 아이디인지 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<AvailableTofinIdServiceResponse> isAvailableTofinId(
        @Parameter(description = "검사할 아이디", required = true) @RequestParam String target) {
        return ApiUtils.success(
            "아이디 사용 가능한지 조회", isAvailableTofinIdUseCase.isAvailableTofinId(target));
    }
}
