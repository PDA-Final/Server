package com.pda.challengeapplication.emojis;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Emoji", description = "투핀 감정 저축 챌린지 이모지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/challenges/emoji")
public class EmojiController {
    private final EmojiService emojiService;

    @PostMapping
    @Operation(summary = "감정 이모지 등록", description = "감정 이모지를 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse<Emoji> registerEmoji(
            @Valid @RequestBody EmojiRequestDTO emojiRequestDTO){
        Emoji savedEmoji= emojiService.createEmoji(emojiRequestDTO);
        return ApiUtils.success("이모지 등록", savedEmoji);
    }

    @GetMapping
    @Operation(summary = "전체 감정 이모지 조회", description = "등록된 감정 이모지들을 모두 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<List<Emoji>> findAllEmoji(){
        List<Emoji> emojis= emojiService.findAllEmoji();
        return ApiUtils.success("전체 이모지 조회",emojis );
    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "이모지 상세 조회", description = "감정 이모지를 개별 조회합니다")
//    @ApiResponse(responseCode = "200", description = "성공")
//    public GlobalResponse<Emoji> findEmojiById(
//            @PathVariable(value = "id") Integer id
//    ){
//        Emoji emojis= emojiService.findEmojiById(id);
//        return ApiUtils.success("전체 이모지 조회",emojis );
//    }
}
