package com.pda.userapplication.services.in.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SetTendencyServiceRequest {
    private Long userId;
    private boolean account;
    private boolean card;
    private boolean loan;
    private boolean invest;
    private String purpose;
}
