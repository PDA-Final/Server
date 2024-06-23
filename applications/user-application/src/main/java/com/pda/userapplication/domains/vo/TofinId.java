package com.pda.userapplication.domains.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TofinId {
    private String tofinId;

    public static TofinId of(String tofinId) {
        if (!isTofinIdFormat(tofinId))
            throw new IllegalArgumentException("Invalid tofinId: " + tofinId);

        return new TofinId(tofinId);
    }

    public static boolean isTofinIdFormat(String tofinId) {
        return Pattern.matches("^[a-zA-Z0-9]{8,30}$", tofinId);
    }

    @Override
    public String toString() {
        return tofinId;
    }
}