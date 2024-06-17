package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import com.pda.userapplication.inadapters.controllers.dto.req.ConnectAssetsRequest;
import com.pda.userapplication.inadapters.controllers.dto.req.SetPublicOptionRequest;
import com.pda.userapplication.inadapters.controllers.dto.req.SetTendencyRequest;
import com.pda.userapplication.services.in.ConnectAssetUseCase;
import com.pda.userapplication.services.in.GetJobsUseCase;
import com.pda.userapplication.services.in.IsAvailableContact;
import com.pda.userapplication.services.in.IsAvailableTofinIdUseCase;
import com.pda.userapplication.services.in.SetPublicOptionUseCase;
import com.pda.userapplication.services.in.SetTendencyUseCase;
import com.pda.userapplication.services.in.dto.req.ConnectAssetsServiceRequest;
import com.pda.userapplication.services.in.dto.req.SetPublicOptionServiceRequest;
import com.pda.userapplication.services.in.dto.req.SetTendencyServiceRequest;
import com.pda.userapplication.services.in.dto.res.AvailableContactServiceResponse;
import com.pda.userapplication.services.in.dto.res.AvailableTofinIdServiceResponse;
import com.pda.userapplication.services.in.dto.res.ConnectAssetInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final ConnectAssetUseCase connectAssetUseCase;
    private final IsAvailableContact isAvailableContact;
    private final SetPublicOptionUseCase setPublicOptionUseCase;
    private final SetTendencyUseCase setTendencyUseCase;

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

    @PostMapping("/assets")
    @Operation(summary = "자산 연결", description = "자산 연결 API (Generate)",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse<List<ConnectAssetInfoResponse>> connectAssets(@AuthUser AuthUserInfo authUser, @Valid @RequestBody ConnectAssetsRequest request) {
        return ApiUtils.success("자산 연결 성공", connectAssetUseCase.connectAssets(ConnectAssetsServiceRequest.builder()
            .backSocialId(request.getBackSocialId())
            .contact(request.getContact())
            .userId(authUser.getId())
            .socialName(request.getSocialName())
            .build()));
    }

    @GetMapping("/available-contact")
    @Operation(summary = "휴대전화 확인", description = "전화번호 사용 가능한지 확인")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<AvailableContactServiceResponse> isAvailableContact(@Parameter(description = "확인할 전화번호(- 빼기)", example = "01012341234") @RequestParam(name = "target") String target) {
        return ApiUtils.success("유저 전화번호 확인", isAvailableContact.isAvailableContact(target));
    }

    @PostMapping("/public-options")
    @Operation(summary = "자산 공개 설정", description = "유저 자산 공개 옵션 설정",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse<Void> setPublicOption(@AuthUser AuthUserInfo authUser, @Valid @RequestBody SetPublicOptionRequest request) {
        setPublicOptionUseCase.setPublicOption(SetPublicOptionServiceRequest.builder()
                .publicPercent(request.isPercent())
                .userId(authUser.getId())
                .publicAmount(request.isAmount())
            .build());

        return ApiUtils.success("유저 자산 공개 설정");
    }

    @PostMapping("/tendency")
    @Operation(summary = "투자 성향 설정", description = "유저의 투자성향을 설정하는 API",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse<Void> setTendency(@AuthUser AuthUserInfo authUser, @Valid @RequestBody SetTendencyRequest request) {
        setTendencyUseCase.setTendency(SetTendencyServiceRequest.builder()
                .card(request.isCard())
                .loan(request.isLoan())
                .account(request.isAccount())
                .invest(request.isInvest())
                .purpose(request.getPurpose())
                .userId(authUser.getId())
            .build());
        return ApiUtils.success("유저 성향 설정 완료");
    }
}
