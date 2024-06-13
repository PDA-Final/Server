package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.Comment;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.CommentRepository;
import com.pda.boardapplication.utils.UserUtils;
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

    /**
     * Register comment
     * @param boardId target board's id
     * @param registerReqDto
     * @return comment id
     */
    public long registerComment(long boardId, CommentDto.RegisterReqDto registerReqDto, UserDto.InfoDto userInfoDto) {
        log.debug("Register comment to board {}, parent: {}", boardId, registerReqDto.getParentId());

        Comment comment = Comment.builder()
                .content(registerReqDto.getContent())
                .board(boardRepository.getReferenceById(boardId))
                .parentComment(registerReqDto.getParentId() == 0 ? null : commentRepository.getReferenceById(registerReqDto.getParentId()))
                .userId(userInfoDto.getId())
                .authorNickname(userInfoDto.getNickname())
                .authorProfile(userInfoDto.getProfile())
                .authorType(UserUtils.getUserRoleCode(userInfoDto.getType()))
                .build();

        return commentRepository.save(comment).getId();
    }

    /**
     * Get list of comments
     * @param boardId target board id
     * @return list of CommentInfoDto
     */
    public List<CommentDto.CommentInfoDto> getCommentList(long boardId) {

        List<Comment> comments = commentRepository.findAllByBoardId(boardId).orElse(new ArrayList<>());

        log.info("Total {} comments found for board {}", comments.size(), boardId);

        return comments.stream()
                .filter((comment) -> comment.getParentComment() == null)
                .map((comment ->
                        CommentDto.CommentInfoDto.builder()
                                .content(comment.getContent())
                                .authorId(comment.getUserId())
                                .authorName(comment.getAuthorNickname())
                                .authorProfile(comment.getAuthorProfile())
                                .replies(comment.getReplies().stream().map((reply) ->
                                                CommentDto.ReplyInfoDto.builder()
                                                        .content(reply.getContent())
                                                        .authorId(reply.getUserId())
                                                        .authorName(reply.getAuthorNickname())
                                                        .authorProfile(reply.getAuthorProfile())
                                                        .createdTime(reply.getCreatedAt())
                                                        .build()
                                        ).toList())
                                .createdTime(comment.getCreatedAt())
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

        commentRepository.delete(comment);
        return 1;
    }
}
