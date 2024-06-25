package com.pda.boardapplication.service;

import com.pda.boardapplication.dto.UserDto;
import com.pda.boardapplication.entity.*;
import com.pda.boardapplication.repository.*;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ConflictException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardInteractionService {

    @Value("${out-service.credit.url}")
    private String creditServerUrl;
    private final RestTemplate restTemplate;
    private final ProducerService producerService;

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

    /**
     * Unlock board item
     * @param boardId target board's id
     * @param userInfoDto request user's info
     * @return affected count
     * @throws NotFoundException target board not found
     */
    public int unlockBoard(long boardId, UserDto.InfoDto userInfoDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new NotFoundException("Target board does not exists"));

        if(!board.isLocked())
            throw new BadRequestException("Target board is not locked");

        Unlocked unlocked = Unlocked.builder().board(board).userId(userInfoDto.getId()).build();
        if(unlockedRepository.existsById(new UnlockedPK(boardId, userInfoDto.getId()))) {
            throw new ConflictException("Already unlocked");
        }

        getTransferResponse(boardId, 10, userInfoDto.getToken());
        unlockedRepository.save(unlocked);

        return 1;
    }

    /**
     * Perform transfer
     * @param targetId target user's id
     * @param amount credit amount to transfer
     * @param token user's access token
     * @throws BadRequestException failed to transfer
     */
    private void getTransferResponse(long targetId, int amount, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Bearer %s", token));

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("toUserId", targetId);
        reqBody.put("amount", amount);
        reqBody.put("transactionDateTime", LocalDateTime.now());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(reqBody, headers);

        log.warn(creditServerUrl);
        ResponseEntity<Object> response
                = restTemplate.exchange(
                creditServerUrl + "/credit/transfer", HttpMethod.POST,
                entity, Object.class);

        log.info("Response from credit : {}", response.getStatusCode());

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new BadRequestException("Failed to transfer");
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
