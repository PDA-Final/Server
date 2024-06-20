package com.pda.challengeapplication.challenges.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.CorpChallengeDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostCorpChallengeRequest {
    long id;
    @Schema(description = "챌린지 타입(기업은 0)", example = "0")
    @NotNull(message = "챌린지 타입이 입력되지 않았습니다")
    int challengeType;
    @Schema(description = "챌린지 이름", example = "커뮤니티 월간 챌린지")
    @NotNull(message = "챌린지 이름이 입력되지 않았습니다")
    String name;
    @Schema(description = "챌린지 설명", example = "")
    @NotNull(message = "챌린지 설명이 입력되지 않았습니다")
    String description;
    @Schema(description = "챌린지 로고", example = ".png")
    @NotNull(message = "챌린지 로고가 입력되지 않았습니다")
    String logoUrl;
    @Schema(description = "챌린지 모집 시작일", example = "2024-06-19")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String startAt;
    @Schema(description = "챌린지 모집 시작일", example = "2025-06-19")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String  endAt;
    @Schema(description = "챌린지 기간", example = "30")
    Integer term;
    @Schema(description = "주관 기업 ID", example = "27")
    long corpId;
    @Schema(description = "주관 기업 이름", example = "신한투자증권")
    String corpName;
    @Schema(description = "챌린지 이동 url", example = "https://m.shinhansec.com/mweb/anev/evnt/aevnt0001?tab=1")
    String challengeUrl;

    public Challenge convertToChallengeEntity() {
        return new Challenge(id, challengeType, name, description, logoUrl, LocalDate.parse(startAt), LocalDate.parse(endAt), term, null,null);
    }

    public CorpChallengeDetail convertToCCDEntity(Challenge challenge) {
        return new CorpChallengeDetail(id,corpId, corpName, challengeUrl,challenge);
    }
}
