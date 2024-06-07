package com.pda.userapplication.services.auth.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SignUpServiceRequest {
    private String tofinId;
    private String userInfo;
}
