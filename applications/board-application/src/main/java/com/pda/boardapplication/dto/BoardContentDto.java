package com.pda.boardapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

public class BoardContentDto {

    @NoArgsConstructor
    @Getter
    public static class OutputDataDto {
        private Timestamp time;
        private String version;
        private List<BlockDto> blocks;
    }

    @NoArgsConstructor
    @Getter @Setter
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = false)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = ParagraphBlockDto.class, name = "paragraph"),
            @JsonSubTypes.Type(value = ImageBlockDto.class, name = "image"),
            @JsonSubTypes.Type(value = ListBlockDto.class, name = "list")
    })
    public static class BlockDto {
        private String id;
        private String type;
    }

    @Getter
    public static class ParagraphBlockDto extends BlockDto {
        ParagraphDto data;

        public ParagraphBlockDto() {
            super.setType("paragraph");
        }
    }

    @Getter
    public static class ImageBlockDto extends BlockDto {
        ImageDto data;

        public ImageBlockDto() {
            super.setType("image");
        }
    }

    @Getter
    public static class ListBlockDto extends BlockDto {
        ListDto data;

        public ListBlockDto() {
            super.setType("list");
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ParagraphDto {
        private String text;
    }

    @NoArgsConstructor
    @Getter
    public static class ImageDto {
        private FileDto file;
        private String caption;
    }

    @NoArgsConstructor
    @Getter
    public static class FileDto {
        private String url;
    }

    @NoArgsConstructor
    @Getter
    public static class ListDto {
        private List<String> items;
        private String style;
    }
}
