package com.pda.userapplication.services.in.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DetailPortfolio {
    private Long totalAmount;
    private Double savingRate;
    private Long savingAmount;
    private Double depositRate;
    private Long depositAmount;
    private Double cmaRate;
    private Long cmaAmount;
    private Double investRate;
    private Long investAmount;

    private Double returnRate;
    private Double domesticRatio;
    private Double foreignRatio;

    private List<StockResponse> domesticStocks;
    private List<StockResponse> foreignStocks;
}
