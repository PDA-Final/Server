package com.pda.boardapplication.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateAuthorRequest {
    private Long id;
    private String nickname;
    private int userType;
    private String profileImage;
}
