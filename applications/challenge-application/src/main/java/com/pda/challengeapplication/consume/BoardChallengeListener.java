package com.pda.challengeapplication.consume;

import com.pda.challengeapplication.mychallenges.dto.request.PostMyBoardChallengeRequest;
import com.pda.challengeapplication.mychallenges.service.MyBoardChallengeService;
import com.pda.kafkautils.board.BoardPostSuccessDto;
import com.pda.kafkautils.challenge.ChallengeSuccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BoardChallengeListener {
    private final MyBoardChallengeService myBoardChallengeService;

    @KafkaListener(topics = "board-post", concurrency = "3")
    public void listenBoardPost(BoardPostSuccessDto boardPost) {
        log.info("consume: board-post at challenge service");
        myBoardChallengeService.writeBoardChallenge(PostMyBoardChallengeRequest.builder()
                        .boardId(boardPost.getBoardId())
                        .usesrId(boardPost.getUserId())
                        .challengeId(boardPost.getChallengeId())
                .build());
    }

    @KafkaListener(topics = "challenge-success", concurrency = "3")
    public void listenChallengeSuccess(ChallengeSuccessDto challengeSuccessDto) {
        myBoardChallengeService.successBoardChallenge(challengeSuccessDto.getBoardId());
    }
}
