package com.pda.userapplication.outadapters.redis;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@RedisHash(value = "refresh", timeToLive = (30 * 24 * 60 * 60))
public class RefreshInfoEntity {
    @Id
    private Long id; // userId
    private String role;
    private String nickname;
    private String profile;
    private LocalDate birth;
    private String job;

    @Indexed
    private String refreshToken;
}

