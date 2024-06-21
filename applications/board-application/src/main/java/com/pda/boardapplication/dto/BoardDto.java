package com.pda.boardapplication.dto;

import com.pda.boardapplication.entity.Category;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @AllArgsConstructor
    @Getter
    @Schema(description = "Response body of registered board")
    public static class RegisteredRespDto {
        @Schema(description = "Registered board's id")
        private long boardId;
    }

    @AllArgsConstructor
    @Getter
    @Schema(description = "Response body of modify/delete board")
    public static class UpdatedCountRespDto {
        @Schema(description = "Number of modified/deleted boards")
        private int updatedCnt;
    }

    @Builder
    @Getter
    @Schema(description = "Response body of board detail")
    public static class DetailRespDto {
        @Schema(description = "Board title", example = "⭐️ 내가 들었던 펀드 추천 글 ⭐")
        private String title;
        @Schema(description = "Board Content - will be modified",example = "Soon will be modified")
        private String content;
        @Schema(description = "Category id and name")
        private Category category;
        @Schema(description = "Count of likes of board", example = "32")
        private int likeCount;
        @Schema(description = "Count of comments of board", example = "7")
        private int commentCount;
        @Schema(description = "List of comments including replies")
        private List<CommentDto.CommentInfoDto> comments;
        @Schema(description = "Registered time", example = "2024-06-13T14:59:28.115615")
        private LocalDateTime createdTime;
        @Schema(description = "id of author", example = "1")
        private long authorId;
        @Schema(description = "Nickname of author", example = "podif")
        private String authorNickname;
        @Schema(description = "Img url of author's profile image", example = "https://picsum.photos/seed/picsum/200/200")
        private String authorProfile;

        // TODO : tagged product/challenges
    }

    @Builder
    @Getter
    @Schema(description = "Abstract board information")
    public static class AbstractRespDto {
        @Schema(description = "Board id", example = "2")
        private long id;
        @Schema(description = "Board title", example = "⭐️ 내가 들었던 펀드 추천 글 ⭐")
        private String title;
        @Schema(description = "Summary of content", example = "오늘은 내가 들었던 펀드 중에 제일 좋았던 신한은행의 ...")
        private String summary;
        @Schema(description = "[Not Implemented Yet] Url of thumbnail image", example = "https://picsum.photos/seed/picsum/200/200")
        private String thumbnail;
        @Schema(description = "Registered time", example = "2024-06-13T14:59:28.115615")
        private LocalDateTime createdTime;
        @Schema(description = "Count of likes of board", example = "32")
        private int likeCount;
        @Schema(description = "Count of comments of board", example = "7")
        private int commentCount;
        @Schema(description = "Nickname of author", example = "shs")
        private String authorNickname;

    }

    @NoArgsConstructor
    @Getter
    @Setter
    @Schema(description = "[Not Implemented Yet] Request query parameter to search | sort board list")
    public static class SearchConditionDto {
        @Schema(description = "category id : 1(정보), 2(재미), 3(투자), 4(기업), 5(고급)",
                allowableValues = {"1","2","3","4","5"},
                example = "2",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Parameter(required = false)
        private String category;
        @Schema(description = "Sorting condition", example = "인기순",
                allowableValues = {"인기순", "최신순"},
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String sort;
        @Schema(description = "Author's id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Parameter
        private int userId;
    }
}
