package com.pda.boardapplication.service;

import com.pda.kafkautils.KafkaJson;
import com.pda.kafkautils.alert.AlertMessageDto;
import com.pda.kafkautils.board.BoardPostSuccessDto;
import com.pda.kafkautils.challenge.ChallengeSuccessDto;
import com.pda.kafkautils.credit.AddCreditDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, KafkaJson> kafkaTemplate;

    @Async
    public void sendBoardChallengePosted(long userId, long challengeId, long boardId) {
        log.info("Produce: board challenge registered");
        kafkaTemplate.send("board-post", BoardPostSuccessDto.builder()
                .userId(userId).challengeId(challengeId).boardId(boardId).build());
    }

    @Async
    public void sendBoardChallengeSuccess(long boardId) {
        log.info("Produce: board challenge success");
        kafkaTemplate.send("challenge-success", ChallengeSuccessDto.builder().boardId(boardId).build());
    }

    public void sendBoardAlertPosted(long clientId) {
        log.info("Produce: board posted alert");
        kafkaTemplate.send("alert-msg",
                AlertMessageDto.builder()
                    .messageType("CREDIT").clientId(clientId).targetId(clientId)
                    .content("핀 작성으로 1 크레딧을 획득하셨습니다.")
                    .build());
    }

    public void sendBoardCreditAcquired(long userId, long amount) {
        log.info("Produce: credit acquired : {}", amount);
        kafkaTemplate.send("add-credit",
                AddCreditDto.builder()
                    .userId(userId).amount(amount)
                    .transactionDateTime(LocalDateTime.now()).build());
    }

    public void sendCommentAlertPosted(long clientId, String userNickname, long boardId, String thumbnail) {
        log.info("Produce: comment posted to board : {}", boardId);

        kafkaTemplate.send("alert-msg", AlertMessageDto.builder()
                .messageType("FIN").clientId(clientId).targetId(boardId)
                .content(String.format("%s님이 회원님의 핀을 좋아합니다.", userNickname))
                .thumbnail(thumbnail)
                .build()
        );
    }

    public void sendLikeAlertPosted(long clientId, String userNickname, long boardId, String thumbnail) {
        log.info("Produce: like has been posted to board : {}", boardId);

        kafkaTemplate.send("alert-msg", AlertMessageDto.builder()
                .messageType("FIN").clientId(clientId).targetId(boardId)
                .content(String.format("%s님이 회원님의 핀을 좋아합니다.", userNickname))
                .thumbnail(thumbnail)
                .build()
        );
    }

    public void sendBoardAlertUnlocked(long clientId, long targetId, long boardId, String thumbnail) {
        log.info("Produce: board {} has been unlocked by user {}", boardId, clientId);
        kafkaTemplate.send("alert-msg", AlertMessageDto.builder()
                .messageType("FOLLOW").clientId(clientId).targetId(targetId)
                .content(String.format("%s님이 회원님의 잠겨진 핀을 열람하여 10 크레딧을 획득하셨습니다."))
                .thumbnail(thumbnail)
                .build()
        );
    }
}
