package com.pda.userapplication.services.in.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "팔로우 상태 조회")
public class FollowStatusServiceResponse {
    @Schema(name = "팔로우 중 인지", example = "false")
    @JsonProperty("isFollow")
    private boolean follow;
    @Schema(name = "팔로워 수", example = "4")
    private Long followers;
    @Schema(name = "팔로잉 수", example = "5")
    private Long followings;
}
