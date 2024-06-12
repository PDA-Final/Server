package com.pda.challengeapplication.emojis;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmojiRequestDTO {

    Integer id;
    @NotNull(message = "감정이 입력되지 않았습니다")
    String emotion;
    @NotNull(message = "이미지Url이 입력되지 않았습니다")
    String imgUrl;
    @NotNull(message = "저축 금액이 입력되지 않았습니다")
    int price;

    public Emoji converToEmojiEntity(){
        return new Emoji(id,emotion, imgUrl, price);
    }

}
