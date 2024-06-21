package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.BoardDto;
import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.Board;
import com.pda.boardapplication.entity.BoardCount;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.CategoryRepository;
import com.pda.boardapplication.utils.UserUtils;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ForbiddenException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import com.pda.s3utils.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CategoryRepository categoryRepository;

    private final BoardCountRepository boardCountRepository;

    /**
     * Register Board
     * @param registerReqDto data to register board
     * @return registered id
     * @throws BadRequestException - given boardId does not exist
     */
    public long registerBoard(BoardDto.RegisterReqDto registerReqDto, UserDto.InfoDto authorInfoDto) {
        log.debug("Register Board with title : {}, user name : {}", registerReqDto.getTitle(), authorInfoDto.getNickname());

        Board board = Board.builder()
                .title(registerReqDto.getTitle())
                .content(registerReqDto.getContent())
                .category(categoryRepository.getReferenceById(registerReqDto.getCategoryId()))
                .userId(authorInfoDto.getId())
                .authorNickname(authorInfoDto.getNickname())
                .authorType(UserUtils.getUserRoleCode(authorInfoDto.getType()))
                .authorProfile(authorInfoDto.getProfile())
                .build();

        long boardId = boardRepository.save(board).getId();

        boardCountRepository.save(BoardCount.builder()
                        .board(board).likeCnt(0).viewCnt(0).build());
        return boardId;
    }

    /**
     * Get board detail
     * @param boardId target board id
     * @return
     * @throws com.pda.exceptionhandler.exceptions.NotFoundException - target does not exists
     */
    public BoardDto.DetailRespDto getBoardDetail(long boardId, UserDto.InfoDto userInfoDto) {
        log.debug("Get detail of board : {}", boardId);

        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundException::new);

        return BoardDto.DetailRespDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .category(board.getCategory())
                // TODO : comments and replies
                .comments(board.getComments().stream().filter((comment) ->
                        comment.getParentComment() == null
                ).map((elem) ->
                    CommentDto.CommentInfoDto.builder()
                            .id(elem.getId())
                            .content(elem.getContent())
                            .authorId(elem.getUserId())
                            .authorName(elem.getAuthorNickname())
                            .authorProfile(elem.getAuthorProfile())
                            .replies(elem.getReplies().stream().map((el) ->
                                CommentDto.ReplyInfoDto.builder()
                                        .id(el.getId())
                                        .content(el.getContent())
                                        .authorId(el.getUserId())
                                        .authorName(el.getAuthorNickname())
                                        .authorProfile(el.getAuthorProfile())
                                        .createdTime(el.getCreatedAt())
                                        .build()
                            ).toList())
                            .createdTime(elem.getCreatedAt())
                            .build()
                ).toList())
                .likeCount(board.getLikes().size())
                .commentCount(board.getComments().size())
                .createdTime(board.getCreatedAt())
                .authorId(board.getUserId())
                .authorNickname(board.getAuthorNickname())
                .authorProfile(board.getAuthorProfile())
                .liked(userInfoDto != null &&
                        board.getLikes().stream().anyMatch(elem ->
                        elem.getUserId() == userInfoDto.getId()))
                .bookmarked(userInfoDto != null &&
                        board.getBookmarks().stream().anyMatch(elem ->
                        elem.getUserId() == userInfoDto.getId()))
                .build();
    }

    /**
     * Get board list by page
     * @param pageNo page num
     * @param size page size
     * @return
     */
    public List<BoardDto.AbstractRespDto> getBoards(
            int pageNo, int size,
            BoardDto.SearchConditionDto searchConditionDto
    ) {
        List<Board> boards;
        log.info("search dto : {}", searchConditionDto);
        Sort sort = getSortBySearchCondition(searchConditionDto);
        Pageable pageable = PageRequest.of(pageNo, size, sort);

        if(searchConditionDto.getCategory() != null) {
            log.info("Search by category : {}", searchConditionDto.getCategory());
            boards = boardRepository.findByCategoryId(pageable, Integer.parseInt(searchConditionDto.getCategory())).getContent();
        } else if(searchConditionDto.getUserId() > 0) {
            log.info("Search by user id : {}", searchConditionDto.getUserId());
            boards = boardRepository.findByUserId(pageable, searchConditionDto.getUserId()).getContent();
        } else {
            log.info("No adequate search conditions found");
            boards = boardRepository.findAll(pageable).getContent();
        }

        return boards.stream().map((elem) ->
                BoardDto.AbstractRespDto.builder()
                        .id(elem.getId())
                        .title(elem.getTitle())
                        .summary(elem.getContent())
                        .createdTime(elem.getCreatedAt())
    //                    .thumbnail()
                        .likeCount(elem.getLikes().size())
                        .commentCount(elem.getComments().size())
                        .authorNickname(elem.getAuthorNickname())
                        .build()
        ).toList();
    }

    /**
     * Modify board
     * @param boardId
     * @param modifyReqDto
     * @return 1 for success
     */
    public int modifyBoard(long boardId, BoardDto.ModifyReqDto modifyReqDto, UserDto.InfoDto userInfoDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundException::new);
        if(board.getUserId() != userInfoDto.getId())
            throw new ForbiddenException("Illegal access to board by unauthorized user");
        board.updateEntity(modifyReqDto.getTitle(), modifyReqDto.getContent());

        boardRepository.save(board);
        return 1;
    }

    /**
     * Delete board
     * @param boardId
     * @return
     */
    public int deleteBoard(long boardId, UserDto.InfoDto userInfoDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundException::new);
        if(board.getUserId() != userInfoDto.getId())
            throw new ForbiddenException(("Illegal access to board by unauthorized user"));

        boardRepository.delete(board);
        return 1;
    }

    /**
     * Return Sort object by given search condition
     * @param searchConditionDto search conditions from client
     * @return Sort object
     */
    private Sort getSortBySearchCondition(BoardDto.SearchConditionDto searchConditionDto) {
        if(searchConditionDto != null) {
            log.info(searchConditionDto.getSort());
            return "인기순".equals(searchConditionDto.getSort()) ?
                Sort.by(Sort.Direction.DESC, "boardCount.likeCnt")
                : Sort.by(Sort.Direction.DESC, "createdAt");

        }

        return Sort.by(Sort.Direction.DESC, "createdAt");
    }
}
