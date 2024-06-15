package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.Bookmark;
import com.pda.boardapplication.entity.Like;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.BookmarkRepository;
import com.pda.boardapplication.repository.LikeRepository;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardInteractionService {

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final BookmarkRepository bookmarkRepository;


    /**
     * Toggle status of like by user
     * @param boardId target board id
     * @param userInfoDto request user's info
     * @return in/decrement
     * @throws NotFoundException target board not found
     */
    public int toggleLike(long boardId, UserDto.InfoDto userInfoDto) {
        if(!boardRepository.existsById(boardId))
            throw new NotFoundException("Target board does not exist");

        Like like = Like.builder()
                .board(boardRepository.getReferenceById(boardId))
                .userId(userInfoDto.getId())
                .build();

        if(!likeRepository.exists(Example.of(like))) {
            likeRepository.save(like);
            return 1;
        } else {
            likeRepository.delete(like);
            return -1;
        }
    }

    public int toggleBookmark(long boardId, UserDto.InfoDto userInfoDto) {
        if(!boardRepository.existsById(boardId))
            throw new NotFoundException("Target board does not exists");

        Bookmark bookmark = Bookmark.builder()
                .board(boardRepository.getReferenceById(boardId))
                .userId(userInfoDto.getId())
                .build();

        if(!bookmarkRepository.exists(Example.of(bookmark))) {
            bookmarkRepository.save(bookmark);
            return 1;
        } else {
            bookmarkRepository.delete(bookmark);
            return -1;
        }
    }
}
