package com.pda.challengeapplication.challenges.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ModifyCorpChallengeRequest {

    @NotNull(message = "챌린지 이름이 입력되지 않았습니다")
    String name;
    @NotNull(message = "챌린지 설명이 입력되지 않았습니다")
    String description;
    String logoUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    LocalDate startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    LocalDate endAt;
    String challengeUrl;

}
