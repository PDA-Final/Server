package com.pda.challengeapplication.challenges.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ModifyCorpChallengeRequest {

    @NotNull(message = "챌린지 이름이 입력되지 않았습니다")
    String name;
    @NotNull(message = "챌린지 설명이 입력되지 않았습니다")
    String description;
    String logoUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Timestamp endAt;
    String challengeUrl;

}
