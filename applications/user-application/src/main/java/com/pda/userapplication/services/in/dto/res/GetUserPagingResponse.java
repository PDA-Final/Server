package com.pda.userapplication.services.in.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GetUserPagingResponse {
    @Schema(description = "유저 총 수", example = "9")
    private Long totalCount;
    @Schema(description = "해당 페이지의 마지막 인덱스", example = "5")
    private Long lastIndex;
    @Schema(description = "마지막인지 체크", example = "false")
    private boolean isLast;
    private List<GetUserSummaryResponse> users;
}