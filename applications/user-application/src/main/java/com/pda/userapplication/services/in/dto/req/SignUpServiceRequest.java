package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SignUpServiceRequest {
    private String tofinId;
    private String userInfo;
    private String nickname;
    private String profileImage;
    private LocalDate birth;
    private String job;
}
