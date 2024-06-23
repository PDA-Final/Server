package com.pda.boardapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
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

    @AllArgsConstructor
    @Getter
    @Schema(description = "Response body of registered comment")
    public static class RegisteredRespDto {
        @Schema(description = "Registered comment's id")
        private long commentId;
    }

    @AllArgsConstructor
    @Getter
    @Schema(description = "Response body of modify/delete comment")
    public static class UpdatedCountRespDto {
        @Schema(description = "Number of modified/deleted comments")
        private int updatedCnt;
    }

    @Builder
    @Getter
    @Schema(description = "Comment information")
    public static class CommentInfoDto {
        @Schema(description = "id of comment", example = "123908")
        private long id;
        @Schema(description = "Content of comment", example = "LGTM")
        private String content;
        @Schema(description = "id of author", example = "992")
        private long authorId;
        @Schema(description = "Nickname of author", example = "Swings")
        private String authorName;
        @Schema(description = "Image url of author's profile", example = "https://picsum.photos/seed/picsum/200/300")
        private String authorProfile;
        @Schema(description = "List of replies")
        private List<ReplyInfoDto> replies;
        @Schema(description = "Registered time", example = "2024-06-13T14:59:28.115615")
        private LocalDateTime createdTime;
    }

    @Builder
    @Getter
    @Schema(description = "Reply information")
    public static class ReplyInfoDto {
        @Schema(description = "id of comment", example = "123555463")
        private long id;
        @Schema(description = "Content of comment", example = "FYI")
        private String content;
        @Schema(description = "id of author", example = "991231")
        private long authorId;
        @Schema(description = "Nickname of author", example = "John")
        private String authorName;
        @Schema(description = "Image url of author's profile", example = "https://picsum.photos/seed/picsum/200/300")
        private String authorProfile;
        @Schema(description = "Registered time", example = "2024-06-13T14:59:28.115615")
        private LocalDateTime createdTime;
    }
}
