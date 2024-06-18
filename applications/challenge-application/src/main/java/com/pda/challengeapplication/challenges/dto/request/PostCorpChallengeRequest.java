package com.pda.challengeapplication.challenges.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.CorpChallengeDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostCorpChallengeRequest {
    long id;
    @NotNull(message = "챌린지 타입이 입력되지 않았습니다")
    int challengeType;
    @NotNull(message = "챌린지 이름이 입력되지 않았습니다")
    String name;
    @NotNull(message = "챌린지 설명이 입력되지 않았습니다")
    String description;
    @NotNull(message = "챌린지 로고가 입력되지 않았습니다")
    String logoUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String  endAt;
    Integer term;
    long corpId;
    String corpName;
    String challengeUrl;

    public Challenge convertToChallengeEntity() {
        return new Challenge(id, challengeType, name, description, logoUrl, LocalDate.parse(startAt), LocalDate.parse(endAt), term, null,null);
    }

    public CorpChallengeDetail convertToCCDEntity(Challenge challenge) {
        return new CorpChallengeDetail(id,corpId, corpName, challengeUrl,challenge);
    }
}
