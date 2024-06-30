package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.Board;
import com.pda.boardapplication.entity.Comment;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.CommentRepository;
import com.pda.boardapplication.utils.UserUtils;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ForbiddenException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final ProducerService producerService;

    /**
     * Register comment
     * @param boardId target board's id
     * @param registerReqDto
     * @return comment id
     */
    public long registerComment(long boardId, CommentDto.RegisterReqDto registerReqDto, UserDto.InfoDto userInfoDto) {
        log.debug("Register comment to board {}, parent: {}", boardId, registerReqDto.getParentId());

        Comment parentComment = registerReqDto.getParentId() == 0 ? null
                : verifyParentComment(boardId, registerReqDto.getParentId());

        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new BadRequestException("Invalid Target board id"));

        Comment comment = Comment.builder()
                .content(registerReqDto.getContent())
                .board(board)
                .parentComment(parentComment)
                .userId(userInfoDto.getId())
                .authorNickname(userInfoDto.getNickname())
                .authorProfile(userInfoDto.getProfile())
                .authorType(UserUtils.getUserRoleCode(userInfoDto.getType()))
                .build();

        long commentId = commentRepository.save(comment).getId();

        if(board.getUserId() != userInfoDto.getId())
            producerService.sendCommentAlertPosted(board.getUserId(),
                    userInfoDto.getNickname(), boardId, board.getThumbnail());

        return commentId;
    }

    /**
     * Get list of comments
     * @param boardId target board id
     * @return list of CommentInfoDto
     */
    public List<CommentDto.CommentInfoDto> getCommentList(long boardId) {

        List<Comment> comments = commentRepository.findAllByBoardId(boardId).orElse(new ArrayList<>());

        log.debug("Total {} comments found for board {}", comments.size(), boardId);

        return comments.stream()
                .filter((comment) -> comment.getParentComment() == null)
                .map((comment ->
                        CommentDto.CommentInfoDto.builder()
                                .id(comment.getId())
                                .content(comment.isDeleted() ? "삭제된 댓글입니다." : comment.getContent())
                                .authorId(comment.isDeleted() ? 0L : comment.getUserId())
                                .authorName(comment.isDeleted() ? "anonymous" : comment.getAuthorNickname())
                                .authorProfile(comment.isDeleted() ? "" : comment.getAuthorProfile())
                                .replies(comment.getReplies().stream().map((reply) ->
                                                CommentDto.ReplyInfoDto.builder()
                                                        .id(reply.getId())
                                                        .content(reply.getContent())
                                                        .authorId(reply.getUserId())
                                                        .authorName(reply.getAuthorNickname())
                                                        .authorProfile(reply.getAuthorProfile())
                                                        .createdTime(reply.getCreatedAt())
                                                        .build()
                                        ).toList())
                                .createdTime(comment.getCreatedAt())
                                .deleted(comment.isDeleted())
                                .build()
                )).toList();
    }

    /**
     * Modify Comment
     * @param commentId target comment id
     * @param modifyReqDto Info to modify
     * @param userInfoDto  request user's info
     * @return modified count
     * @throws NotFoundException target comment not found
     * @throws ForbiddenException illegal access by unauthorized user
     */
    public int modifyComment(long commentId, CommentDto.ModifyReqDto modifyReqDto, UserDto.InfoDto userInfoDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundException::new);

        if(comment.getUserId() != userInfoDto.getId())
            throw new ForbiddenException("Illegal access to comment by unauthorized user");
        comment.updateEntity(modifyReqDto.getComment());

        commentRepository.save(comment);
        return 1;
    }

    /**
     * Delete Comment
     * @param commentId target comment id
     * @param userInfoDto request user's info
     * @return modified count
     * @throws NotFoundException target comment not found
     * @throws ForbiddenException Illegal access by unauthorized user
     */
    public int deleteComment(long commentId, UserDto.InfoDto userInfoDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundException::new);

        if(comment.getUserId() != userInfoDto.getId())
            throw new ForbiddenException("Illegal access to comment by unauthorized user");

        if(doSoftDelete(comment)) {
            log.info("Cannot delete comment : {} | {}", comment.getParentComment(), comment.getReplies().size());
            comment.updateSoftDelete();
            commentRepository.save(comment);
        } else {
            log.info("No siblings, delete comment");
            commentRepository.delete(comment);

            // TODO check parent comment
            if(comment.getParentComment().getReplies().isEmpty() && comment.getParentComment().isDeleted())
                commentRepository.delete(comment.getParentComment());
        }
        return 1;
    }

    /**
     * Check whether soft delete must be performed to comment or not
     * @param comment target comment
     * @return whether soft delete must be performed or not
     */
    private boolean doSoftDelete(Comment comment) {
        log.info("Given comment : {} | {}", comment.getParentComment(), comment.getReplies().size());
        if(comment.getParentComment() != null || comment.getReplies().isEmpty())
            return false;

        else return true;
    }

    /**
     * Verify given comment id and board id
     * @param boardId target board id
     * @param parentCommentId target parent id
     * @return parent comment
     * @throws NotFoundException target comment does not exist
     * @throws BadRequestException target comment does not belong to target board or deleted
     */
    private Comment verifyParentComment(long boardId, long parentCommentId) {
        Comment parentComment = commentRepository.findById(parentCommentId).orElseThrow(() ->
            new NotFoundException("Parent Comment does not exists")
        );
        if(parentComment.isDeleted())
            throw new BadRequestException("Comment has been already deleted");
        if(parentComment.getBoard().getId() != boardId)
            throw new BadRequestException("Comment does not belong to given board id");

        return parentComment;
    }
}
