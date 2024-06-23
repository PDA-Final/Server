package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import com.pda.userapplication.inadapters.controllers.dto.req.ConnectAssetsRequest;
import com.pda.userapplication.inadapters.controllers.dto.req.SetPublicOptionRequest;
import com.pda.userapplication.inadapters.controllers.dto.req.SetTendencyRequest;
import com.pda.userapplication.inadapters.controllers.dto.req.UpdateProfileRequest;
import com.pda.userapplication.services.in.ConnectAssetUseCase;
import com.pda.userapplication.services.in.GetJobsUseCase;
import com.pda.userapplication.services.in.GetUserDetailInfo;
import com.pda.userapplication.services.in.GetUserUseCase;
import com.pda.userapplication.services.in.IsAvailableContact;
import com.pda.userapplication.services.in.IsAvailableTofinIdUseCase;
import com.pda.userapplication.services.in.SetPublicOptionUseCase;
import com.pda.userapplication.services.in.SetTendencyUseCase;
import com.pda.userapplication.services.in.UpdateUserUseCase;
import com.pda.userapplication.services.in.dto.req.ConnectAssetsServiceRequest;
import com.pda.userapplication.services.in.dto.req.SearchUserServiceRequest;
import com.pda.userapplication.services.in.dto.req.SetPublicOptionServiceRequest;
import com.pda.userapplication.services.in.dto.req.SetTendencyServiceRequest;
import com.pda.userapplication.services.in.dto.req.UpdateProfileServiceRequest;
import com.pda.userapplication.services.in.dto.res.AvailableContactServiceResponse;
import com.pda.userapplication.services.in.dto.res.AvailableTofinIdServiceResponse;
import com.pda.userapplication.services.in.dto.res.ConnectAssetInfoResponse;
import com.pda.userapplication.services.in.dto.res.GetUserPagingResponse;
import com.pda.userapplication.services.in.dto.res.UserDetailInfoResponse;
import com.pda.userapplication.services.in.dto.res.UserServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    private final GetUserDetailInfo getUserDetailInfo;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;

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
        security = @SecurityRequirement(name = "bearerAuth"), deprecated = true)
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

    @GetMapping("/detail-info")
    @Operation(summary = "유저 민감 정보 조회", description = "유저의 민감 정보 조회(자산 연결 후 사용 가능) -> 클라이언트에서 절대 사용 X",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<UserDetailInfoResponse> getDetailInfo(@AuthUser AuthUserInfo userInfo) {
        return ApiUtils.success("민감 정보 조회 성공", getUserDetailInfo.getUserDetailInfo(userInfo.getId()));
    }

    @PutMapping(path = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "유저 프로필 수정", description = "유저 프로필 설정",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<Void> updateProfile(@AuthUser AuthUserInfo authUser,
                                              @RequestPart(name = "image", required = false) MultipartFile multipartFile,
                                              @Parameter(name = "request") @RequestPart(name = "request", required = false) UpdateProfileRequest request) {
        if (multipartFile == null && request == null)
            throw new BadRequestException("변경사항이 없습니다.");

        UpdateProfileServiceRequest.UpdateProfileServiceRequestBuilder builder = UpdateProfileServiceRequest.builder()
            .userId(authUser.getId())
            .profileImage(multipartFile);

        if (request != null) {
            builder
                .job(request.getJob())
                .nickname(request.getNickname());
        }

        updateUserUseCase.updateProfile(builder.build());
        return ApiUtils.success("유저 프로필 수정");
    }

    @GetMapping("/{id}")
    @Operation(summary = "유저 상세 조회", description = "유저의 기본 정보 조회 -> 인증 필수 아님",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<UserServiceResponse> getUser(@AuthUser AuthUserInfo authUser, @PathVariable(name = "id") Long id) {
        return ApiUtils.success("유저 조회 성공", getUserUseCase.findById(
            id, authUser==null?null:authUser.getId()));
    }

    @GetMapping("/search")
    @Operation(summary = "유저 검색", description = "유저 닉네임으로 검색")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<GetUserPagingResponse> searchByNickname(
          @Parameter(name = "nickname", required = true, example = "동원참치") @RequestParam(name = "nickname") String nickname,
          @Parameter(name = "limit", description = "가져올 갯수(디폴트 20)", example = "20") @RequestParam(name = "limit", defaultValue = "20", required = false) Long limit,
          @Parameter(name = "last", description = "마지막으로 조회한 팔로우 id", example = "203") @RequestParam(name = "last", required = false) Long last) {
        return ApiUtils.success("유저 검색 성공", getUserUseCase.searchUserByNickname(SearchUserServiceRequest.builder()
                .lastIndex(last)
                .limit(limit)
                .nickname(nickname)
            .build()));
    }

    @GetMapping("/{id}/portfolios")
    @Operation(summary = "유저 포트폴리오 조회", description = "유저 포트폴리오 조회 -> 인증 필수 아님",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<Void> getPortfolios(@AuthUser AuthUserInfo authUser, @PathVariable("id") Long id) {
        return ApiUtils.success("유저 포트폴리오 조회 완료");
    }

    @GetMapping("/products")
    @Operation(summary = "유저 보유 상품 조회", description = "유저 본인의 보유 상품 조회",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<Void> getAsets(@AuthUser AuthUserInfo authUser) {
        return ApiUtils.success("유저 보유 상품 조회 완료");
    }
}
