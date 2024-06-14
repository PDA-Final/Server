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
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "[Comment]")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(description = "Register comment", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<CommentDto.RegisteredRespDto> registerComment(
            @RequestBody CommentDto.RegisterReqDto registerReqDto,
            @PathVariable("boardId") long boardId,
            @AuthUser TokenableUser user
    ) {
        log.debug("Register comment to board : {}", boardId);

        try {
            UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromTokenableUser(user);

            long commentId = commentService.registerComment(boardId, registerReqDto, userInfoDto);
            return ApiUtils.created("created", new CommentDto.RegisteredRespDto(commentId));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Board does not exists");
        }
    }

    @GetMapping
    @Operation(description = "Load comments of board")
    public GlobalResponse<List<CommentDto.CommentInfoDto>> getCommentList(@PathVariable("boardId") long boardId) {
        log.debug("Get list of board : {}", boardId);

        List<CommentDto.CommentInfoDto> commentList = commentService.getCommentList(boardId);

        return ApiUtils.success("success", commentList);
    }

    @PutMapping("/{commentId}")
    @Operation(description = "Modify comment", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<CommentDto.UpdatedCountRespDto> modifyComment(
            @PathVariable("commentId") long commentId,
            @RequestBody CommentDto.ModifyReqDto modifyReqDto,
            @AuthUser TokenableUser user
    ) {
        log.debug("Modify comment : {} by user {}", commentId, user.getId());
        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromTokenableUser(user);

        int count = commentService.modifyComment(commentId, modifyReqDto, userInfoDto);

        return ApiUtils.success("success", new CommentDto.UpdatedCountRespDto(count));
    }

    @DeleteMapping("/{commentId}")
    @Operation(description = "Delete comment", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<CommentDto.UpdatedCountRespDto> deleteComment(
            @PathVariable("commentId") long commentId,
            @AuthUser TokenableUser user
    ) {
        log.debug("Delete comment : {} by user {}", commentId, user.getId());
        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromTokenableUser(user);

        int count = commentService.deleteComment(commentId, userInfoDto);

        return ApiUtils.success("success", new CommentDto.UpdatedCountRespDto(count));
    }
}
