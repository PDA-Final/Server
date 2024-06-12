package com.pda.boardapplication.dto;

import com.pda.boardapplication.entity.Category;
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
    @Schema(description = "To register board")
    public static class RegisterReqDto {
        @NotBlank
        @Schema(description = "board title")
        private String title;
        @NotBlank
        @Schema(description = "board content - soon about to be modified")
        private String content;
        @DecimalMax(value = "5", inclusive = true)
        @Schema(description = "category id", allowableValues = {"1","2","3","4","5"})
        private int categoryId;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "To modify board")
    public static class ModifyReqDto {
        @Schema(description = "board title", nullable = true)
        private String title;
        @Schema(description = "board content - soon about to be modified", nullable = true)
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
    public static class SearchConditionDto {
        private String category;
        private String sort;
        private int userId;
    }
}
