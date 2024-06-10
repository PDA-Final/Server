package com.pda.challengeapplication.challenges;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pda.challengeapplication.challenges.repository.Challenge;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ChallengeDTO {
    int id;
    @NotNull(message = "챌린지 타입이 입력되지 않았습니다")
    int challengeType;
    @NotNull(message = "챌린지 이름이 입력되지 않았습니다")
    String name;
    int participants;
    @NotNull(message = "챌린지 설명이 입력되지 않았습니다")
    String description;
    @NotNull(message = "챌린지 로고가 입력되지 않았습니다")
    String logoUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Timestamp endAt;


    public Challenge convertToEntity(){return new Challenge(id,challengeType,name,participants,description,logoUrl,startAt,endAt);}


}
