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
public class AbstractPortfolio {
    private Long totalAmount;
    private Double savingRate;
    private Double depositRate;
    private Double cmaRate;
    private Double investRate;

    private Double returnRate;
    private Double domesticRatio;
    private Double foreignRatio;

    private List<StockResponse> domesticStocks;
    private List<StockResponse> foreignStocks;
}
