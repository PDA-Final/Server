package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.CommentDto;
import com.pda.boardapplication.entity.Comment;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public long registerComment(long boardId, CommentDto.RegisterReqDto registerReqDto) {
        log.debug("Register comment to board {}, parent: {}", boardId, registerReqDto.getParentId());
        Comment comment = Comment.builder()
                .content(registerReqDto.getContent())
                .board(boardRepository.getReferenceById(boardId))
                .parentComment(registerReqDto.getParentId() == 0 ? null : commentRepository.getReferenceById(registerReqDto.getParentId()))
                .build();

        return commentRepository.save(comment).getId();
    }
}
