package com.pda.userapplication.services.in.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Schema(description = "유저 민감정보 Response")
public class UserDetailInfoResponse {
    @Schema(description = "유저 실명", example = "김동원")
    private String socialName;
    @Schema(description = "유저 주민등록번호 앞자리", example = "000103")
    private String frontSocialId;
    @Schema(description = "유저 주민등록번호 뒷자리", example = "12341234")
    private String backSocialId;
    @Schema(description = "유저 전화번호", example = "01012341234")
    private String contact;
}
