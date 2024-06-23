package com.pda.userapplication.inadapters.controllers.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "로그인 Request Body")
public class SignInRequest {
    @NotBlank(message = "tofin id는 꼭 필요합니다")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,30}$", message = "유저의 아이디는 5자~30자 이내의 영문과 숫자로만 이루어져야 합니다.")
    @Schema(description = "유저의 아이디", example = "dongwon000103", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tofinId;

    @NotBlank(message = "birth는 비어있으면 안됩니다")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,30}$", message = "user info는 8자~30자 이내의 영문과 숫자로만 이루어져야 합니다")
    @Schema(description = "userInfo", example = "tkjadfjkls12", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userInfo;
}
