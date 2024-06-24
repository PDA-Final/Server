package com.pda.userapplication.services.in.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pda.tofinenums.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "유저 Res")
public class UserServiceResponse {
    @Schema(description = "유저 id(PK)", example = "1")
    private Long id;
    @Schema(description = "유저 닉네임", example = "동원참치")
    private String nickname;
    @Schema(description = "유저 프로필 이미지", example = "유저 프로필 이미지")
    private String profileImage;
    @Schema(description = "유저 투핀 아이디", example = "dongwon0103")
    private String tofinId;
    @Schema(description = "유저 롤", example = "NORMAL")
    private UserRole role;
    @Schema(description = "유저 직업", example = "대학생")
    private String job;
    @Schema(description = "유저 연령대", example = "30")
    private Integer ageRange;
    @Schema(description = "팔로우 중 인지", example = "false")
    @JsonProperty("isFollow")
    private boolean follow;
    @Schema(description = "팔로워 수", example = "4")
    private Long followers;
    @Schema(description = "팔로잉 수", example = "5")
    private Long followings;
}
