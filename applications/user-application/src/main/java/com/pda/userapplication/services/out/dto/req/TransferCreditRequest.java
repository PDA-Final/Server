package com.pda.userapplication.services.out.dto.req;

import com.pda.userapplication.domains.vo.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferCreditRequest {
    private Long amount;
    private String token;
    private UserId toUserId;
}
