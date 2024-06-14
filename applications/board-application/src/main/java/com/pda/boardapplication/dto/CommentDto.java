package com.pda.boardapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDto {

    @NoArgsConstructor
    @Getter
    @Schema(description = "Request Body to register comment")
    public static class RegisterReqDto {
        @Schema(description = "Parent comment's id",
                example = "1", nullable = true,
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private long parentId;
        @NotBlank
        @Schema(description = "Comment",
                example = "좋은 정보 감사합니다 *^^*",
                requiredMode = Schema.RequiredMode.REQUIRED)
        private String content;
    }

    @NoArgsConstructor
    @Getter
    @Schema(description = "Request body to Modify comment")
    public static class ModifyReqDto {
        @NotBlank
        @Schema(description = "Comment",
                example = "수정된 댓글",
                requiredMode = Schema.RequiredMode.REQUIRED)
        private String comment;
    }

    @Builder
    @Getter
    public static class CommentInfoDto {
        private long id;
        private String content;
        private long authorId;
        private String authorName;
        private String authorProfile;
        private List<ReplyInfoDto> replies;
        private LocalDateTime createdTime;
    }

    @Builder
    @Getter
    public static class ReplyInfoDto {
        private long id;
        private String content;
        private long authorId;
        private String authorName;
        private String authorProfile;
        private LocalDateTime createdTime;
    }
}
