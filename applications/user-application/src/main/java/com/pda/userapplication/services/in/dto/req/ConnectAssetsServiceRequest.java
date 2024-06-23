package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ConnectAssetsServiceRequest {
    private Long userId;
    private String socialName;
    private String backSocialId;
    private String contact;
}
