package com.pda.userapplication.inadapters.controllers.user;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.userapplication.services.in.GetJobsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "투핀 유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final GetJobsUseCase getJobsUseCase;

    @GetMapping("/jobs")
    @Operation(summary = "직업 리스트 조회", description = "모든 직업들의 리스트를 한글로 반환합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<String>> getJobs() {
        return ApiUtils.success(
            "직업 리스트 조회", getJobsUseCase.getJobs());
    }
}
