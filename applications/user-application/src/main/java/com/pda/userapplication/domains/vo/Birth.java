package com.pda.userapplication.domains.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Birth {
    private LocalDate birth;

    public static Birth of(LocalDate birth) {
        if (birth == null)
            return null;

        if (!birth.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Birth must be not null and before now");

        return new Birth(birth);
    }

    public int getAge() {
        return LocalDate.now().getYear() - birth.getYear();
    }

    public LocalDate toLocalDate() {
        return birth;
    }
}
