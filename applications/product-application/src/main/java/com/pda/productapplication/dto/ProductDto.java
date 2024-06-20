package com.pda.productapplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ProductDto {
    @Builder
    @Getter
    public static class BasicRespDto {
        private String name;
        private String corpName;
        private String corpImage;
        private String cardImage;
        private List<String> tags;
        private LocalDateTime createdTime;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class SearchConditionDto {
        private String category;
        private String sort;

        // TODO : 관련 게시글 작성자 수
    }

    @Builder
    @Getter
    public static class CardSummaryRespDto {
        private String notice;
        private String annualFee;
        private String rewards;
        private String baseRecord;
        private String mainBenefit;
        private String subBenefit;
    }

    @Builder
    @Getter
    public static class CardDetailRespDto {
        private String description;
        private List<String> terms;
    }

    @Builder
    @Getter
    public static class SavingSummaryRespDto {
        private float interestRate;
        private float primeInterestRate;
        private String savingTerm;
        private String specialOfferSummary;
        private String specialOfferPeriod;
    }

    @Builder
    @Getter
    public static class SavingDetailRespDto {
        private String joinPeriod;
        private String joinAmount;
        private String joinTarget;
        private String specialConditions;
        private String channel;
        private String interestPaymentCycle;
        private String note;
        private String depositorProtection;
    }

    @Builder
    @Getter
    public static class FundSummaryRespDto {
        private String fundCode;
        private float stdPrice; // 기준가
        private float diffPrice; // 변동가
        private float drvNav; // 운용규모
        private float rt3m; // 수익률(3달)
        private float ter; // 총보수
    }

    @Builder
    @Getter
    public static class FundDetailRespDto {
        private float rt1m; // 수익률(1달)
        private float rt3m; // 수익률(3달)
        private float rt6m; // 수익률(6달)
        private float rtYtd; // 수익률(연초)
        private float rt1y; // 수익률(1년)
        private float rt3y; // 수익률(3년)
        private float rt5y; // 수익률(5년)
        private int riskGrade; // 위험등급(숫자)
        private String riskGradeText; // 위험등급(텍스트)
        private String feeGb; // 수수료
        private String category1; // 펀드유형1
        private String category2; // 펀드유형2
        private String infoObject; // 투자목적
        private String infoStrategy; // 투자전략
        private String region; // 투자지역
        private String amtGb; // 규모성장
        private String exceBm; // BM초과성과
        private String riskGb; // 위험도
        private String rtGb; // 성과지속
    }

    @Builder
    @Getter
    public static class LoanSummaryRespDto {
        private float minInterestRate;
        private float maxInterestRate;
        private String maxLoanAmount;
    }

    @Builder
    @Getter
    public static class LoanDetailRespDto {
        private String description;
    }

    @Builder
    @Getter
    public static class BoardCountReqDto {
        private int boardCount;
    }
}