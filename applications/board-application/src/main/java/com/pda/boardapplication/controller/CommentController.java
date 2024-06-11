package com.pda.boardapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(description = "Register comment")
    public GlobalResponse<Object> registerComment(@RequestBody CommentDto.RegisterReqDto registerReqDto, @PathVariable("boardId") long boardId) {
        Map<String, Object> result = new HashMap<>();

        log.debug("board id : {}", boardId);
        long commentId = commentService.registerComment(boardId, registerReqDto);

        result.put("commentId", commentId);
        return ApiUtils.created("created", result);
    }
}
