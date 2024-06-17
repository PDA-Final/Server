package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetPublicOptionServiceRequest {
    private Long userId;
    private boolean publicAmount;
    private boolean publicPercent;
}
