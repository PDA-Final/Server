package com.pda.userapplication.services.out.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PortfolioResponse {
    private String code;
    private String stockType;
    private String name;
    private String engName;
    private String dartCode;
    private int price;
    private Double dollar;
    private int quantity;
}
