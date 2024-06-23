package com.pda.userapplication.domains.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Nickname {
    private String nickname;

    public static Nickname of(String nickname) {
        if (!isCorrectNickname(nickname))
            throw new IllegalArgumentException("Nickname is not correct");

        return new Nickname(nickname);
    }

    public static boolean isCorrectNickname(String nickname) {
        return Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,30}$", nickname);
    }


    @Override
    public String toString() {
        return nickname;
    }
}
