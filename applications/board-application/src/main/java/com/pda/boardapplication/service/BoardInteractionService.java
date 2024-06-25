package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.*;
import com.pda.boardapplication.repository.BoardCountRepository;
import com.pda.boardapplication.repository.BoardRepository;
import com.pda.boardapplication.repository.BookmarkRepository;
import com.pda.boardapplication.repository.LikeRepository;
import com.pda.boardapplication.utils.ChallengeUtils;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import com.pda.kafkautils.board.BoardPostSuccessDto;
import com.pda.kafkautils.challenge.ChallengeSuccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardInteractionService {

    private final ProducerService producerService;

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BoardCountRepository boardCountRepository;


    /**
     * Toggle status of like by user
     * @param boardId target board id
     * @param userInfoDto request user's info
     * @return in/decrement
     * @throws NotFoundException target board not found
     */
    public int toggleLike(long boardId, UserDto.InfoDto userInfoDto) {
        int ret = 0;

        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new NotFoundException("Target board does not exist"));

        Like like = Like.builder()
                .board(boardRepository.getReferenceById(boardId))
                .userId(userInfoDto.getId())
                .build();
        // Error InvalidDataAccessApiUsageException : circular
//        if(!likeRepository.exists(Example.of(like))) {
        if(!likeRepository.existsById(new LikePK(boardId, userInfoDto.getId()))) {
            likeRepository.save(like);

            long challengeId = checkBoardChallengeSuccess(boardId);

            if(challengeId > -1)
                producerService.sendBoardChallengeSuccess(
                        ChallengeSuccessDto.builder().boardId(boardId).build());

            producerService.sendLikeAlertPosted(board.getUserId(), userInfoDto.getNickname(),
                    boardId, board.getThumbnail());

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

    /**
     * Check whether board challenge has been completed
     * @param boardId target board id
     * @return challenge id, -1 if false
     */
    private long checkBoardChallengeSuccess(long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);

        ChallengeUtils.BoardChallengeInfo taggedChallengeInfo =
                ChallengeUtils.getBoardChallengeInfoById(
                        board.getTaggedChallenges().get(0).getChallengeId());

        log.info("Tagged challenge for board {} was : {}", boardId, taggedChallengeInfo);

        if((taggedChallengeInfo != null) &&
                (board.getLikes().size() == taggedChallengeInfo.getSuccessThreshold()))
            return board.getTaggedChallenges().get(0).getChallengeId();

        return -1;
    }
}
