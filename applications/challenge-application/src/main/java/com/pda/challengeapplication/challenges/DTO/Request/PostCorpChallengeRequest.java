package com.pda.challengeapplication.challenges.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.CorpChallengeDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Timestamp;
@Getter
public class PostCorpChallengeRequest {
    Integer id;
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
    int term;
    Integer corpId;
    String corpName;
    String challengeUrl;

    public Challenge convertToChallengeEntity() {
        return new Challenge(id, challengeType, name, participants, description, logoUrl, startAt,endAt, term);
    }

    public CorpChallengeDetail convertToCCDEntity(Challenge challenge) {
        return new CorpChallengeDetail(id,corpId, corpName, challengeUrl,challenge);
    }
}
