package com.pda.boardapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.boardapplication.dto.BoardDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.exceptions.LockedBoardException;
import com.pda.boardapplication.service.BoardInteractionService;
import com.pda.boardapplication.service.BoardService;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

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
    private final BoardInteractionService boardInteractionService;

    @PostMapping
    @Operation(summary = "Register board item", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<BoardDto.RegisteredRespDto> registerBoard(@RequestBody @Valid BoardDto.RegisterReqDto registerReqDto, @AuthUser AuthUserInfo user) {
        log.debug("Register Board with title : {}", registerReqDto.getTitle());
        log.debug("Parsing jwt, got user id : {}", user.getId());

        try {
            UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromAuthUserInfo(user);
            long boardId = boardService.registerBoard(registerReqDto, userInfoDto);
            log.debug("Board Item registered with PK : {}", boardId);

            return ApiUtils.created("created", new BoardDto.RegisteredRespDto(boardId));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Invalid category Id");
        }

    }

    @GetMapping("/{boardId}")
    @Operation(summary = "Get board item detail", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<BoardDto.DetailRespDto> getBoardDetail(@PathVariable("boardId") long boardId, @AuthUser AuthUserInfo user) {
        log.debug("Retrieve board with id : {}", boardId);
        UserDto.InfoDto userInfoDto = user != null ?
                UserDto.InfoDto.fromAuthUserInfo(user) : null;


        BoardDto.DetailRespDto detailRespDto = boardService.getBoardDetail(boardId, userInfoDto);

        return ApiUtils.success("success", detailRespDto);
    }

    @ExceptionHandler(LockedBoardException.class)
    public GlobalResponse<BoardDto.LockedContentRespDto> handleLockedBoardException(LockedBoardException e) {
        BoardDto.LockedContentRespDto lockedContentRespDto =
                BoardDto.LockedContentRespDto.builder()
                        .unlockedCount(e.getUnlockedCount())
                        .likedCount(e.getLikeCount())
                        .build();

        return ApiUtils.success("success", lockedContentRespDto);
    }

    @GetMapping
    @Operation(summary = "Get board list")
    public GlobalResponse<List<BoardDto.AbstractRespDto>> getBoardList(
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @Parameter BoardDto.SearchConditionDto searchConditionDto
    ) {
        log.debug("Get board lists with page {}, size {}", pageNo, size);

        log.debug("{}", searchConditionDto);

        List<BoardDto.AbstractRespDto> boards = boardService.getBoards(pageNo, size, searchConditionDto);

        return ApiUtils.success("success", boards);
    }

    @PutMapping("/{boardId}")
    @Operation(summary = "Modify board", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<BoardDto.UpdatedCountRespDto> modifyBoard(
            @RequestBody BoardDto.ModifyReqDto modifyReqDto,
            @PathVariable("boardId") long boardId,
            @AuthUser AuthUserInfo user
    ) {
        log.debug("Update board : {}", boardId);

        if((modifyReqDto.getTitle() == null || modifyReqDto.getTitle().isBlank())
            && (modifyReqDto.getContent() == null || modifyReqDto.getContent().getBlocks() == null)) {
            throw new BadRequestException("At least one property required");
        }

        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromAuthUserInfo(user);

        int count = boardService.modifyBoard(boardId, modifyReqDto, userInfoDto);

        return ApiUtils.success("success", new BoardDto.UpdatedCountRespDto(count));
    }

    @DeleteMapping("/{boardId}")
    @Operation(summary = "Delete board", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<BoardDto.UpdatedCountRespDto> deleteBoard(@PathVariable("boardId") long boardId, @AuthUser AuthUserInfo user) {

        log.debug("Delete board : {}", boardId);

        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromAuthUserInfo(user);
        int count = boardService.deleteBoard(boardId, userInfoDto);

        return ApiUtils.success("success", new BoardDto.UpdatedCountRespDto(count));
    }

    @PostMapping("/{boardId}/like")
    @Operation(summary = "Post like toggle event", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<Object> toggleLike(
            @PathVariable("boardId") long boardId,
            @AuthUser AuthUserInfo user
    ) {
        log.debug("Post like to board {}", boardId);
        Map<String, Object> result = new HashMap<>();

        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromAuthUserInfo(user);
        int count = boardInteractionService.toggleLike(boardId, userInfoDto);
        result.put("modifiedStatus", count > 0 ? "created" : "canceled");

        log.debug("Like to board {} toggled : {}", boardId, count);

        return count > 0 ?
                ApiUtils.created("created",result) :
                ApiUtils.success("deleted", result);
    }

    @PostMapping("/{boardId}/bookmark")
    @Operation(summary = "Post bookmark toggle event", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<Object> toggleBookmark(
            @PathVariable("boardId") long boardId,
            @AuthUser AuthUserInfo user
    ) {
        log.debug("Post bookmark to board {}", boardId);
        Map<String, Object> result = new HashMap<>();

        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromAuthUserInfo(user);
        int count = boardInteractionService.toggleBookmark(boardId, userInfoDto);
        result.put("modifiedStatus", count > 0 ? "created" : "canceled");

        log.debug("Bookmark of board {} toggled {}", boardId, count);

        return count > 0 ?
                ApiUtils.created("created", result) :
                ApiUtils.success("deleted", result);
    }

    @PostMapping("/{boardId}/unlock")
    @Operation(summary = "Unlock board item using credit", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<String> unlockBoard(@PathVariable("boardId") long boardId, @AuthUser AuthUserInfo user) {
        log.debug("Unlock board {} by user {}", boardId, user.getId());

        UserDto.InfoDto userInfoDto = UserDto.InfoDto.fromAuthUserInfo(user);

        boardInteractionService.unlockBoard(boardId, userInfoDto);

        return ApiUtils.success("success");
    }

    @GetMapping("/boards/tagged")
    @Operation(summary = "Get tagged boards", security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<List<BoardDto.AbstractRespDto>> getTaggedBoards(
            @RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, defaultValue = "0", value = "productId") long productId,
            @RequestParam(required = false, defaultValue = "0", value = "challengeId") long challengeId
    ) {
        log.debug("Get Tagged boards by product : {} | challenge : {}", productId, challengeId);

        if((productId == 0 && challengeId == 0) || productId != 0 && challengeId !=0)
            throw new BadRequestException("At least one or Only one of tagged target required");

        List<BoardDto.AbstractRespDto> boards = boardService.getTaggedBoards(pageNo, size, productId, challengeId);


        return ApiUtils.success("success", boards);
    }
}
