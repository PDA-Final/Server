package com.pda.userapplication.inadapters.controllers.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "회원가입 Request Body")
public class SignUpRequest {
    @NotBlank(message = "tofin id는 꼭 필요합니다")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,30}$", message = "유저의 아이디는 5자~30자 이내의 영문과 숫자로만 이루어져야 합니다.")
    @Schema(description = "유저의 아이디", example = "dongwon000103", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tofinId;

    @NotBlank(message = "birth는 비어있으면 안됩니다")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,30}$", message = "user info는 8자~30자 이내의 영문과 숫자로만 이루어져야 합니다")
    @Schema(description = "userInfo", example = "tkjadfjkls12", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userInfo;

    @NotBlank(message = "profileImg는 비어있으면 안됩니다")
    @URL(message = "URL 형식이 아닙니다")
    @Schema(description = "프로필 이미지 URL", example = "http://이미지주소")
    private String profileImg;

    @NotBlank(message = "nickname은 비어있으면 안됩니다")
    @Length(min = 2, max = 30, message = "닉네임은 2~30 글자이어야 합니다")
    @Schema(description = "유저의 닉네임", example = "동원참치", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @NotNull(message = "birth는 비어있으면 안됩니다")
    @Past(message = "생일은 오늘보다 이전이어야 합니다")
    @Schema(description = "생년월일", example = "2000-01-03", pattern = "yyyy-MM-dd", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birth;

    @Schema(description = "직업", example = "대학생", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String job;
}
