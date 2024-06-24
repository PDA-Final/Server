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
public class CardResponse {
    private Long id;
    private String corpName;
    private String cardNumber;
    private String name;
    private String image;
}
