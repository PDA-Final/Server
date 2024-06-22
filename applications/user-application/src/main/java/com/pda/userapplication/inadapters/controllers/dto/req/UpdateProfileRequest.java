package com.pda.userapplication.inadapters.controllers.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Schema(description = "유저 프로필 설정 Req")
public class UpdateProfileRequest implements Serializable {
    @Schema(description = "유저의 닉네임", example = "동원참치2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickname;
    @Schema(description = "직업", example = "기타", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String job;
}
