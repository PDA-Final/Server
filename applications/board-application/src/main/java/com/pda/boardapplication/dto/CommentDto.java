package com.pda.boardapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDto {

    @NoArgsConstructor
    @Getter
    @Schema(description = "To register comment")
    public static class RegisterReqDto {
        @Schema(description = "Parent comment's id", nullable = true)
        private long parentId;
        @Schema(description = "Comment")
        private String content;
    }

    @NoArgsConstructor
    @Getter
    @Schema(description = "Modify comment")
    public static class ModifyReqDto {
        @Schema(description = "Comment")
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
