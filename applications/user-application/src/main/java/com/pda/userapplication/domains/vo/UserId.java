package com.pda.userapplication.domains.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserId {
    private Long id;

    public static UserId of(Long id) {
        return new UserId(id);
    }

    public Long toLong() {
        return id;
    }
}
