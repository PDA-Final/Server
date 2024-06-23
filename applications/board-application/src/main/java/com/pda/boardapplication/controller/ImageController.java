package com.pda.boardapplication.controller;

import com.pda.boardapplication.dto.ImageDto;
import com.pda.s3utils.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "[Image]")
@RequestMapping("images")
public class ImageController {

    private final S3Service s3Service;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload image", description = "API is designed for Image Upload from Editor JS ONLY")
    public ResponseEntity<Object> uploadImage(@RequestPart(name = "image") MultipartFile multipartFile) {
        HttpStatus status;
        Map<String, Object> result = new HashMap<>();

        log.info("New image UL request");

        try {
            // TODO Image validation check
            String url = s3Service.upload(multipartFile, "boards");
            result.put("success", 1);
            result.put("file", ImageDto.ImageRespDto.builder().url(url).build());
            status = HttpStatus.CREATED;
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", 0);
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result, status);
    }

    // TODO Delete images
}
