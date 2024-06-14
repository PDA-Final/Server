package com.pda.boardapplication.dto;

import com.pda.boardapplication.entity.Category;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Getter
    @NoArgsConstructor
    @Schema(description = "Request body to register board")
    public static class RegisterReqDto {
        @NotBlank
        @Schema(description = "board title",
                example = "⭐️ 내가 들었던 펀드 추천 글 ⭐",
                requiredMode = Schema.RequiredMode.REQUIRED)
        private String title;
        @NotBlank
        @Schema(description = "board content - soon about to be modified",
                example = "오늘은 내가 들었던 펀드 중에 제일 좋았던 신한은행의 펀드를 이야기해볼게!",
                requiredMode = Schema.RequiredMode.REQUIRED)
        private String content;
        @DecimalMax(value = "5", inclusive = true)
        @Schema(description = "category id : 1(기업), 2(꿀팁), 3(소비), 4(절약), 5(투자)",
                allowableValues = {"1","2","3","4","5"},
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED)
        private int categoryId;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "Request body to register board")
    public static class ModifyReqDto {
        @Schema(description = "board title",
                nullable = true,
                example = "\uD83E\uDE76️️ 내가 들었던 펀드 추천 글 2 \uD83E\uDE76",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String title;
        @Schema(description = "board content - soon about to be modified",
                nullable = true,
                example = "지난번에는 신한이었는데 이번에는 우리은행의 @@@ ...",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String content;
    }

    @Builder
    @Getter
    public static class DetailRespDto {
        private String title;
        private String content;
        private Category category;
        private int likeCount;
        private int commentCount;
        private List<CommentDto.CommentInfoDto> comments;
        private LocalDateTime createdTime;
        private long authorId;
        private String authorNickname;
        private String authorProfile;

        // TODO : tagged product/challenges
    }

    @Builder
    @Getter
    public static class AbstractRespDto {
        private String title;
        private String summary;
        private String thumbnail;
        private LocalDateTime createdTime;
        private int likeCount;
        private int commentCount;
        private String authorNickname;

    }

    @NoArgsConstructor
    @Getter
    @Setter
    @Schema(description = "[Not Implemented Yet] Request query parameter to search | sort board list")
    public static class SearchConditionDto {
        @Schema(description = "category id : 1(기업), 2(꿀팁), 3(소비), 4(절약), 5(투자)",
                allowableValues = {"1","2","3","4","5"},
                example = "2",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Parameter(required = false)
        private String category;
        @Schema(description = "Sorting condition", example = "popular", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Parameter
        private String sort;
        @Schema(description = "Author's id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Parameter
        private int userId;
    }
}
