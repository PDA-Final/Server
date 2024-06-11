package com.pda.boardapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalExceptionResponse;
import com.pda.apiutils.GlobalResponse;
import com.pda.boardapplication.dto.BoardDto;
import com.pda.boardapplication.entity.Board;
import com.pda.boardapplication.exception.NotFoundException;
import com.pda.boardapplication.service.BoardService;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "[Board]")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @Operation(summary = "Register board item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = GlobalResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request body", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    })
    public GlobalResponse<Object> registerBoard(@RequestBody @Valid BoardDto.RegisterReqDto registerReqDto) {
        log.debug("Register Board with title : {}", registerReqDto.getTitle());
        Map<String, Object> result = new HashMap<>();

        try {
            long boardId = boardService.registerBoard(registerReqDto);
            log.debug("Board Item registered with PK : {}", boardId);
            result.put("boardId", boardId);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Invalid category Id");
        }

        return ApiUtils.created("created", result);
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "Get board item detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = GlobalResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    })
    public GlobalResponse<Object> getBoardDetail(@PathVariable("boardId") long boardId) {
        log.debug("Retrieve board with id : {}", boardId);
        Map<String, Object> result = new HashMap<>();

        BoardDto.DetailRespDto detailRespDto = boardService.getBoardDetail(boardId);
        result.put("board", detailRespDto);

        return ApiUtils.success("success", result);
    }

    @GetMapping
    @Operation(summary = "Get board list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No Content")
    })
    public GlobalResponse<Object> getBoardList(
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            BoardDto.SearchConditionDto searchConditionDto
    ) {
        log.debug("Get board lists with page {}, size {}", pageNo, size);
        Map<String, Object> result = new HashMap<>();

        log.info(searchConditionDto.getCategory());

        List<BoardDto.AbstractRespDto> boards = boardService.getBoards(pageNo, size);
        result.put("boards", boards);

        return ApiUtils.success("success", result);
    }

    @PutMapping("/{boardId}")
    @Operation(summary = "Modify board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public GlobalResponse<Object> modifyBoard(
            @RequestBody BoardDto.ModifyReqDto modifyReqDto,
            @PathVariable("boardId") long boardId
    ) {
        log.debug("Update board : {}", boardId);
        Map<String, Object> result = new HashMap<>();

        if((modifyReqDto.getTitle() == null || modifyReqDto.getTitle().isBlank())
            && (modifyReqDto.getContent() == null || modifyReqDto.getContent().isBlank())) {
            throw new BadRequestException("At least one property required");
        }

        int count = boardService.modifyBoard(boardId, modifyReqDto);
        result.put("modified", count);

        return ApiUtils.success("success", result);
    }

    @DeleteMapping("/{boardId}")
    @Operation(summary = "Delete board")
    public GlobalResponse<Object> deleteBoard(@PathVariable("boardId") long boardId) {

        log.debug("Delete board : {}", boardId);
        Map<String, Object> result = new HashMap<>();

        int count = boardService.deleteBoard(boardId);
        result.put("deleted", count);

        return ApiUtils.success("success", result);
    }
}
