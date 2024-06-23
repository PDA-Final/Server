package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import com.pda.userapplication.services.in.FollowUseCase;
import com.pda.userapplication.services.in.dto.req.FollowOrUnFollowServiceRequest;
import com.pda.userapplication.services.in.dto.req.GetFollowableServiceRequest;
import com.pda.userapplication.services.in.dto.res.FollowOrUnfollowServiceResponse;
import com.pda.userapplication.services.in.dto.res.FollowStatusServiceResponse;
import com.pda.userapplication.services.in.dto.res.GetFollowablePagingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow", description = "팔로우 API")
@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowUseCase followUseCase;

    @PostMapping("/users/{id}/follow")
    @Operation(summary = "팔로우 토글", description = "팔로우 ",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalResponse<FollowOrUnfollowServiceResponse> follow(@AuthUser AuthUserInfo authUser,
                                                                  @Parameter(name = "id", description = "유저 아이디값", example = "1") @PathVariable(name = "id") Long id) {
        return ApiUtils.success("(언)팔로우 토글 성공",followUseCase.followOrUnfollow(FollowOrUnFollowServiceRequest.builder()
            .fromId(authUser.getId())
            .toId(id)
            .build()));
    }

    @GetMapping("/users/{id}/followers")
    @Operation(summary = "팔로워", description = "팔로워 조회(인증 필수 아님)",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<GetFollowablePagingResponse> getFollowers(@AuthUser AuthUserInfo authUser,
            @Parameter(name = "id", description = "유저 아이디값", example = "1") @PathVariable(name = "id") Long id,
            @Parameter(name = "limit", description = "가져올 갯수(디폴트 20)", example = "20") @RequestParam(name = "limit", defaultValue = "20", required = false) Long limit,
            @Parameter(name = "last", description = "마지막으로 조회한 팔로우 id", example = "203") @RequestParam(name = "last", required = false) Long last) {
        return ApiUtils.success("팔로워 조회", followUseCase.getFollowers(GetFollowableServiceRequest.builder()
                .toId(id)
                .fromId(authUser==null?null: authUser.getId())
                .limit(limit)
                .lastIndex(last)
            .build()));
    }

    @GetMapping("/users/{id}/follow")
    @Operation(summary = "팔로우 상태", description = "팔로우 상태 조회 (인증 꼭 필요 없음)",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<FollowStatusServiceResponse> getFollowStatus(@AuthUser AuthUserInfo authUser, @PathVariable("id") Long id) {
        return ApiUtils.success("팔로우 상태 조회", followUseCase.getFollow(id, authUser!=null?authUser.getId():null));
    }

    @GetMapping("/users/{id}/followings")
    @Operation(summary = "팔로잉", description = "팔로잉 조회 (인증 꼭 필요 없음), 리스트에서 자기 자신은 false, 자기 자신이 팔로우 하는 사람들은 모두 true",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<GetFollowablePagingResponse> getFollowings(@AuthUser AuthUserInfo authUser,
          @Parameter(name = "id", description = "유저 아이디값", example = "1") @PathVariable(name = "id") Long id,
          @Parameter(name = "limit", description = "가져올 갯수(디폴트 20)", example = "20") @RequestParam(name = "limit", defaultValue = "20", required = false) Long limit,
          @Parameter(name = "last", description = "마지막으로 조회한 팔로우 id", example = "203") @RequestParam(name = "last", required = false) Long last) {
        return ApiUtils.success("팔로잉 조회", followUseCase.getFollowings(GetFollowableServiceRequest.builder()
            .toId(authUser==null?null: authUser.getId())
            .fromId(id)
            .limit(limit)
            .lastIndex(last)
            .build()));
    }
}
