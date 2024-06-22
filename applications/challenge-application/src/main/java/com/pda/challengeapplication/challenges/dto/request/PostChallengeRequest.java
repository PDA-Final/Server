package com.pda.challengeapplication.challenges.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.ChallengeDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostChallengeRequest {
    @Schema(description = "챌린지 타입(자산: 1, 게시글: 2)", example = "1")
    @NotNull(message = "챌린지 타입이 입력되지 않았습니다")
    int challengeType;
    @Schema(description = "챌린지 이름", example = "감정저축챌린지")
    @NotNull(message = "챌린지 이름이 입력되지 않았습니다")
    String name;
    @Schema(description = "챌린지 설명", example = "한 달 동안 감정을 기록하며 저축해보세요")
    @NotNull(message = "챌린지 설명이 입력되지 않았습니다")
    String description;
    @Schema(description = "챌린지 로고", example = "https://upload.wikimedia.org/wikipedia/ko/4/4a/%EC%8B%A0%EC%A7%B1%EA%B5%AC.png")
    @NotNull(message = "챌린지 로고가 입력되지 않았습니다")
    String logoUrl;
    @Schema(description = "챌린지 모집 시작일", example = "2024-06-19")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String startAt;
    @Schema(description = "챌린지 모집 마감일", example = "2040-02-07")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String endAt;
    @Schema(description = "챌린지 기간", example = "30")
    Integer term;
    @Schema(description = "챌린지 상세 정보", example = ".png")
    @NotNull
    String detailDescription;
    @Schema(description = "뱃지 이름", example = "솔직저축왕")
    String badgeName;
    @Schema(description = "챌린지 좋아요 달성 기준", example = "30")
    Integer standard;
    @Schema(description = "블로그 챌린지 작성 카테고리", example = "정보")
    String standardCg;
    @Schema(description = "보상 크레딧", example = "30")
    Integer reward;

    public Challenge convertToChallengeEntity() {
        return new Challenge(null, challengeType, name, description, logoUrl, LocalDate.parse(startAt), LocalDate.parse(endAt), term, null, null);
    }

    public ChallengeDetail convertToCDEntity(long cid, Challenge challenge) {
        return new ChallengeDetail(cid,detailDescription,standard,standardCg,badgeName, reward, challenge);
    }
}
