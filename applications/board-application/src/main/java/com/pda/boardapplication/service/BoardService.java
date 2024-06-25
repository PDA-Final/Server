package com.pda.boardapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pda.boardapplication.dto.BoardContentDto;
import com.pda.boardapplication.dto.BoardDto;
import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.Board;
import com.pda.boardapplication.entity.BoardChallengeTag;
import com.pda.boardapplication.entity.BoardCount;
import com.pda.boardapplication.entity.BoardProductTag;
import com.pda.boardapplication.repository.*;
import com.pda.boardapplication.utils.CategoryUtils;
import com.pda.boardapplication.utils.ChallengeUtils;
import com.pda.boardapplication.utils.UserUtils;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ForbiddenException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import com.pda.kafkautils.alert.AlertMessageDto;
import com.pda.kafkautils.board.BoardPostSuccessDto;
import com.pda.kafkautils.credit.AddCreditDto;
import com.pda.s3utils.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CategoryRepository categoryRepository;

    private final BoardCountRepository boardCountRepository;

    private final BoardProductTagRepository boardProductTagRepository;

    private final BoardChallengeTagRepository boardChallengeTagRepository;

    private final ProducerService producerService;

    private final S3Service s3Service;

    /**
     * Register Board
     * @param registerReqDto data to register board
     * @return registered id
     * @throws BadRequestException - given boardId does not exist
     */
    public long registerBoard(BoardDto.RegisterReqDto registerReqDto, UserDto.InfoDto authorInfoDto) {
        log.debug("Register Board with title : {}, user name : {}", registerReqDto.getTitle(), authorInfoDto.getNickname());

        String [] boardSummary = parseSummary(registerReqDto.getContent());
        // Given category cannot be blank by @NotBlack Validation
        Integer categoryId = CategoryUtils.verifyCategory(registerReqDto.getCategory());

        log.info("Given category was : {} verified to : {}", registerReqDto.getCategory(), categoryId);

        Board board = Board.builder()
                .title(registerReqDto.getTitle())
                .content(boardSummary[2])
                .category(categoryRepository.getReferenceById(categoryId))
                .userId(authorInfoDto.getId())
                .authorNickname(authorInfoDto.getNickname())
                .authorType(UserUtils.getUserRoleCode(authorInfoDto.getType()))
                .authorProfile(authorInfoDto.getProfile())
                .thumbnail(boardSummary[0])
                .summary(boardSummary[1])
                .build();

        long boardId = boardRepository.save(board).getId();

        boardCountRepository.save(BoardCount.builder()
                        .board(board).likeCnt(0).viewCnt(0).build());

        log.debug("tagged onfo {} {}", registerReqDto.getProductId(), registerReqDto.getChallengeId());

        if(registerReqDto.getProductId() > 0) {
            log.debug("Create product tag {} with board {}", registerReqDto.getProductId(), registerReqDto.getProductId());
            boardProductTagRepository.save(BoardProductTag.builder()
                    .board(board).productId(registerReqDto.getProductId()).build());
        }
        if(registerReqDto.getChallengeId() > 0) {
            log.debug("Create product tag {} with challenge {}", registerReqDto.getProductId(), registerReqDto.getChallengeId());
            boardChallengeTagRepository.save(BoardChallengeTag.builder()
                    .board(board).challengeId(registerReqDto.getChallengeId()).build());
        }
        // CREDIT
        if(ChallengeUtils.checkIfBoardChallenge(registerReqDto.getChallengeId())) {
            producerService.sendBoardChallengePosted(
                    BoardPostSuccessDto.builder()
                            .userId(authorInfoDto.getId())
                            .challengeId((long)registerReqDto.getChallengeId())
                            .boardId(boardId)
                    .build());
        }

        producerService.sendBoardAlertPosted(AlertMessageDto.builder()
                .messageType("CREDIT").userId(authorInfoDto.getId())
                .content("핀 작성으로 1 크레딧을 획득하셨습니다.")
                .build());

        producerService.sendBoardCreditAcquired(AddCreditDto.builder()
                .userId(authorInfoDto.getId()).amount(1L)
                .transactionDateTime(LocalDateTime.now()).build());

        return boardId;
    }

    /**
     * Get board detail
     * @param boardId target board id
     * @return
     * @throws NotFoundException - target does not exists
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
                            .content(elem.isDeleted() ? "삭제된 댓글입니다." : elem.getContent())
                            .authorId(elem.isDeleted() ? 0L : elem.getUserId())
                            .authorName(elem.isDeleted() ? "anonymous" : elem.getAuthorNickname())
                            .authorProfile(elem.isDeleted() ? "" : elem.getAuthorProfile())
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
                            .deleted(elem.isDeleted())
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
        Integer categoryId = CategoryUtils.verifyCategory(searchConditionDto.getCategory());

        if(categoryId != null && searchConditionDto.getKeyword() != null) {
            log.info("Search by category and keyword {} | {}", searchConditionDto.getCategory(), searchConditionDto.getKeyword());
            boards = boardRepository.findByCategoryIdAndTitleContains(pageable,
                    categoryId, searchConditionDto.getKeyword()
            ).getContent();

        } else if(categoryId != null) {
            log.info("Search by category : {}", searchConditionDto.getCategory());
            boards = boardRepository.findByCategoryId(pageable, categoryId).getContent();

        } else if(searchConditionDto.getUserId() > 0) {
            log.info("Search by user id : {}", searchConditionDto.getUserId());
            boards = boardRepository.findByUserId(pageable, searchConditionDto.getUserId()).getContent();

        } else if(searchConditionDto.getKeyword() != null) {
            log.info("Search by keyword : {}", searchConditionDto.getKeyword());
            boards = boardRepository.findByTitleContains(pageable, searchConditionDto.getKeyword()).getContent();

        } else {
            log.info("No adequate search conditions found");
            boards = boardRepository.findAll(pageable).getContent();
        }

        return boards.stream().map((elem) ->
                BoardDto.AbstractRespDto.builder()
                        .id(elem.getId())
                        .title(elem.getTitle())
                        .summary(elem.getSummary())
                        .createdTime(elem.getCreatedAt())
                        .thumbnail(elem.getThumbnail())
                        .likeCount(elem.getLikes().size())
                        .commentCount(elem.getComments().size())
                        .authorNickname(elem.getAuthorNickname())
                        .authorProfile(elem.getAuthorProfile())
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

        String [] boardSummary = parseSummary(modifyReqDto.getContent());

        board.updateEntity(modifyReqDto.getTitle(), boardSummary[0], boardSummary[1], boardSummary[2]);

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

        boardRepository.deleteById(boardId);
        return 1;
    }

    public List<BoardDto.AbstractRespDto> getTaggedBoards(int pageNo, int size, long productId, long challengeId) {
        List<Board> boards;
        Pageable pageable = PageRequest.of(pageNo, size);

        if(productId > 0) {
            boards = boardProductTagRepository.findByProductId(pageable, productId)
                    .getContent().stream().map(BoardProductTag::getBoard).toList();
        } else if(challengeId > 0){
            boards = boardChallengeTagRepository.findByChallengeId(pageable, challengeId)
                    .getContent().stream().map(BoardChallengeTag::getBoard).toList();
        } else {
            throw new BadRequestException("At least one of condition required");
        }

        return boards.stream().map((elem) ->
                BoardDto.AbstractRespDto.builder()
                        .id(elem.getId())
                        .title(elem.getTitle())
                        .summary(elem.getSummary())
                        .createdTime(elem.getCreatedAt())
                        .thumbnail(elem.getThumbnail())
                        .likeCount(elem.getLikes().size())
                        .commentCount(elem.getComments().size())
                        .authorNickname(elem.getAuthorNickname())
                        .authorProfile(elem.getAuthorProfile())
                        .build()
        ).toList();
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

    /**
     * Parse summary of content
     * @param outputDataDto data of content
     * @return Array of String : { thumbnail image url, summary, serialized content }
     * @throws BadRequestException Failed to Serialize object
     */
    private String[] parseSummary(BoardContentDto.OutputDataDto outputDataDto) {
        // thumbnail image, summary, serialized
        String[] ret = new String[] {null, null, null};

        ObjectMapper objectMapper = new ObjectMapper();

        for (BoardContentDto.BlockDto blockDto : outputDataDto.getBlocks()) {
            if ("image".equals(blockDto.getType()) && ret[0] == null) {
                ret[0] = ((BoardContentDto.ImageBlockDto) blockDto).getData().getFile().getUrl();
            } else if ("paragraph".equals(blockDto.getType()) && ret[1] == null) {
                ret[1] = ((BoardContentDto.ParagraphBlockDto) blockDto).getData().getText();
            }
        }

        try {
            ret[2] = objectMapper.writeValueAsString(outputDataDto);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Invalid content format. Not Serializable");
        }

        return ret;
    }
}
