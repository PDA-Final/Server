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
@Schema(description = "자산 연결 Request Body")
public class ConnectAssetsRequest {
    @Schema(description = "실명", example = "김동원")
    @NotBlank(message = "실명은 비어있을 수 없습니다.")
    private String socialName;
    @Schema(description = "주민등록번호 뒷자리", example = "1234123")
    @Pattern(regexp = "^[1-4]\\d{6}$", message = "올바르지 않은 주민등록번호 뒷자리입니다.")
    private String backSocialId;
    @Schema(description = "휴대전화번호 (- 없이)", example = "01012341234")
    @Pattern(regexp = "^01(?:0|1|[6-9])\\d{8}", message = "올바르지 않은 휴대전화번호입니다.")
    private String contact;
}
