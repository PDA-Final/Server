package com.pda.challengeapplication.emojis;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmojiRequestDTO {
    long id;
    @Schema(description = "감정 이름", example = "화나요")
    @NotNull(message = "감정이 입력되지 않았습니다")
    String emotion;
    @Schema(description = "이미지 url", example = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQooa1ZACDubTXhszCM8oq0BpjOpt17qE3RQ&s")
    @NotNull(message = "이미지Url이 입력되지 않았습니다")
    String imgUrl;
    @Schema(description = "저축 금액", example = "1818")
    @NotNull(message = "저축 금액이 입력되지 않았습니다")
    int price;

    public Emoji converToEmojiEntity(){
        return new Emoji(id,emotion, imgUrl, price);
    }

}
