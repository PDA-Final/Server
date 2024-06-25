package com.pda.userapplication.services.in.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "포트폴리오 조회")
public class GetPortfolioServiceResponse {
    private DetailPortfolio details;
    private AbstractPortfolio abstracts;

    public static GetPortfolioServiceResponse of(DetailPortfolio details) {
        return new GetPortfolioServiceResponse(details, null);
    }

    public static GetPortfolioServiceResponse of(AbstractPortfolio abstracts) {
        return new GetPortfolioServiceResponse(null, abstracts);
    }
}
