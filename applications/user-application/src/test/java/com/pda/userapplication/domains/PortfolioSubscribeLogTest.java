package com.pda.userapplication.domains;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PortfolioSubscribeLogTest {

    @Test
    void isSubscribed() {
        // given
        LocalDateTime now = LocalDateTime.now();
        PortfolioSubscribeLog sub = PortfolioSubscribeLog.builder()
            .period(1)
            .startAt(now.minusDays(6).minusHours(23).minusMinutes(59))
            .build();

        // when
        boolean result = sub.isSubscribed();

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void isNotSubscribed() {
        // given
        LocalDateTime now = LocalDateTime.now();
        PortfolioSubscribeLog sub = PortfolioSubscribeLog.builder()
            .period(1)
            .startAt(now.minusDays(8))
            .build();

        // when
        boolean result = sub.isSubscribed();

        // then
        Assertions.assertThat(result).isFalse();
    }
}