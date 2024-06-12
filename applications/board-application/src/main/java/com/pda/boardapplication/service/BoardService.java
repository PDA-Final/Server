package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.BoardDto;
import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.Board;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.CategoryRepository;
import com.pda.boardapplication.utils.UserUtils;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ForbiddenException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CategoryRepository categoryRepository;

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

        return boardRepository.save(board).getId();
    }

    /**
     * Get board detail
     * @param boardId target board id
     * @return
     * @throws com.pda.exceptionhandler.exceptions.NotFoundException - target does not exists
     */
    public BoardDto.DetailRespDto getBoardDetail(long boardId) {
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
                            .content(elem.getContent())
                            .createdTime(elem.getCreatedAt())
                            .replies(elem.getReplies().stream().map((el) ->
                                CommentDto.ReplyInfoDto.builder()
                                        .content(el.getContent())
                                        .createdTime(el.getCreatedAt())
                                        .build()
                            ).toList())
                            .build()
                ).toList())
                .likeCount(board.getLikes().size())
                .commentCount(board.getComments().size())
                .createdTime(board.getCreatedAt())
                .authorId(board.getUserId())
                .authorNickname(board.getAuthorNickname())
                .authorProfile(board.getAuthorProfile())
                .build();
    }

    /**
     * Get board list by page
     * @param pageNo page num
     * @param size page size
     * @return
     */
    public List<BoardDto.AbstractRespDto> getBoards(int pageNo, int size) {

        List<Board> boards = boardRepository.findAll(PageRequest.of(pageNo, size)).getContent();

        return boards.stream().map((elem) ->
                BoardDto.AbstractRespDto.builder()
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
}
