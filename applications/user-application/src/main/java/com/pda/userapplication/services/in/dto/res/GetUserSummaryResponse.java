package com.pda.userapplication.services.in.dto.res;

import com.pda.tofinenums.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "유저 Summary Res")
public class GetUserSummaryResponse {
    @Schema(description = "유저ID(PK)", example = "2")
    private Long userId;
    @Schema(description = "유저닉네임", example = "동원참치")
    private String nickname;
    @Schema(description = "유저프로필이미지", example = "http://이미지주소")
    private String profileImage;
    @Schema(description = "유저투핀아이디", example = "dongwon0103")
    private String tofinId;
    @Schema(description = "유저권한", example = "NORMAL")
    private UserRole role;
}
