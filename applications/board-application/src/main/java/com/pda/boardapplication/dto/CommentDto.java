package com.pda.boardapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    @Getter
    public static class CommentInfoDto {
        private String content;
        private long authorId;
        private String authorName;
        private String authorProfile;
        private List<ReplyInfoDto> replies;
    }

    @Builder
    @Getter
    public static class ReplyInfoDto {
        private String content;
        private long authorId;
        private String authorName;
        private String authorProfile;
    }
}
