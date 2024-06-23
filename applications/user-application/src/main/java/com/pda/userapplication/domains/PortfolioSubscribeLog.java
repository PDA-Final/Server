package com.pda.userapplication.domains;

import com.pda.userapplication.domains.vo.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PortfolioSubscribeLog {
    private Long id;
    private Integer period;
    private UserId fromUserId;
    private UserId toUserId;
    private LocalDateTime startAt;

    public boolean isSubscribed() {
        LocalDateTime now = LocalDateTime.now();

        return now.isBefore(startAt.plusDays(period*7));
    }
}
