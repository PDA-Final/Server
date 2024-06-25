package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.*;
import com.pda.boardapplication.repository.*;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardInteractionService {

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BoardCountRepository boardCountRepository;
    private final UnlockedRepository unlockedRepository;


    /**
     * Toggle status of like by user
     * @param boardId target board id
     * @param userInfoDto request user's info
     * @return in/decrement
     * @throws NotFoundException target board not found
     */
    public int toggleLike(long boardId, UserDto.InfoDto userInfoDto) {
        int ret = 0;
        if(!boardRepository.existsById(boardId))
            throw new NotFoundException("Target board does not exist");

        Like like = Like.builder()
                .board(boardRepository.getReferenceById(boardId))
                .userId(userInfoDto.getId())
                .build();
        // Error InvalidDataAccessApiUsageException : circular
//        if(!likeRepository.exists(Example.of(like))) {
        if(!likeRepository.existsById(new LikePK(boardId, userInfoDto.getId()))) {
            likeRepository.save(like);
            ret = 1;
        } else {
            likeRepository.delete(like);
            ret = -1;
        }

        // TODO Update expired(1-2 Week) like counts
        BoardCount boardCount = boardCountRepository.findById(boardId).orElseThrow(NotFoundException::new);
        boardCount.updateOnLike(ret);
        boardCountRepository.save(boardCount);

        return ret;
    }

    /**
     * Toggle status of bookmark by user
     * @param boardId target board id
     * @param userInfoDto request user's info
     * @return in/decrement
     * @throws NotFoundException target board not found
     */
    public int toggleBookmark(long boardId, UserDto.InfoDto userInfoDto) {
        if(!boardRepository.existsById(boardId))
            throw new NotFoundException("Target board does not exists");

        Bookmark bookmark = Bookmark.builder()
                .board(boardRepository.getReferenceById(boardId))
                .userId(userInfoDto.getId())
                .build();

        if(!bookmarkRepository.existsById(new BookmarkPk(boardId, userInfoDto.getId()))) {
            bookmarkRepository.save(bookmark);
            return 1;
        } else {
            bookmarkRepository.delete(bookmark);
            return -1;
        }
    }
}
