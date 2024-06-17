package com.pda.userapplication.services.in.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "연결 자산 Response")
public class ConnectAssetInfoResponse {
    @Schema(description = "계좌 번호, 카드 번호", example = "231-123232-1232")
    private String number;
    @Schema(description = "상품타입", example = "CARD")
    private String productType;
    @Schema(description = "상품이름", example = "정기예금")
    private String name;
    @Schema(description = "보유 현금(카드는 null)", example = "100000")
    private Long cash;
    @Schema(description = "대표 이미지", example = "http://이미지 주소")
    private String image;
}
