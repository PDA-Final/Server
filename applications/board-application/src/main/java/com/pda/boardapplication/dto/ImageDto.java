package com.pda.boardapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class ImageDto {

    @Builder
    @Getter
    @Schema(description = "used for editor js")
    public static class ImageRespDto {
        private String url;
        // TODO additional info
    }
}
