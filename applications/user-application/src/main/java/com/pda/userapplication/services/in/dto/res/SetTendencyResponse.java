package com.pda.userapplication.services.in.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SetTendencyResponse {
    private boolean account;
    private boolean card;
    private boolean loan;
    private boolean invest;
    private String purpose;
}
