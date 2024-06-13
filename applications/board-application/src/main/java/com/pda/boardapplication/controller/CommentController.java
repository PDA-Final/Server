package com.pda.boardapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.service.CommentService;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinsecurity.jwt.TokenableUser;
import com.pda.tofinsecurity.user.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(description = "Register comment", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<Object> registerComment(
            @RequestBody CommentDto.RegisterReqDto registerReqDto,
            @PathVariable("boardId") long boardId,
            @AuthUser TokenableUser user
    ) {
        log.debug("Register comment to board : {}", boardId);
        Map<String, Object> result = new HashMap<>();

        try {
            UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromTokenableUser(user);

            long commentId = commentService.registerComment(boardId, registerReqDto, userInfoDto);
            result.put("commentId", commentId);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Board does not exists");
        }

        return ApiUtils.created("created", result);
    }

    @GetMapping
    @Operation(description = "Load comments of board")
    public GlobalResponse<Object> getCommentList(@PathVariable("boardId") long boardId) {
        log.debug("Get list of board : {}", boardId);
        Map<String, Object> result = new HashMap<>();

        List<CommentDto.CommentInfoDto> commentList = commentService.getCommentList(boardId);

        result.put("commentList", commentList);

        return ApiUtils.success("success", result);
    }

    @PutMapping("/{commentId}")
    @Operation(description = "Modify comment", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<Object> modifyComment(
            @PathVariable("commentId") long commentId,
            @RequestBody CommentDto.ModifyReqDto modifyReqDto,
            @AuthUser TokenableUser user
    ) {
        log.debug("Modify comment : {} by user {}", commentId, user.getId());
        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromTokenableUser(user);
        Map<String, Object> result = new HashMap<>();

        int count = commentService.modifyComment(commentId, modifyReqDto, userInfoDto);
        result.put("modified", count);

        return ApiUtils.success("success", result);
    }

    @DeleteMapping("/{commentId}")
    @Operation(description = "Delete comment", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<Object> deleteComment(
            @PathVariable("commentId") long commentId,
            @AuthUser TokenableUser user
    ) {
        log.debug("Delete comment : {} by user {}", commentId, user.getId());
        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromTokenableUser(user);
        Map<String, Object> result = new HashMap<>();

        int count = commentService.deleteComment(commentId, userInfoDto);
        result.put("deleted", count);

        return ApiUtils.success("success", result);
    }
}
