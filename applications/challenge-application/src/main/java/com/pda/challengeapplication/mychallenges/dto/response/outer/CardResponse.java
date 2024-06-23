package com.pda.challengeapplication.mychallenges.dto.response.outer;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardResponse {
    private Long id;
    private String cardNumber;
    private String name;
    private String image;
}
