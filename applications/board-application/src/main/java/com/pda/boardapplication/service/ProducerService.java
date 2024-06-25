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

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, KafkaJson> kafkaTemplate;

    @Async
    public void sendBoardChallengePosted(BoardPostSuccessDto payload) {
        log.info("Produce: board challenge registered");
        kafkaTemplate.send("board-post", payload);
    }

    @Async
    public void sendBoardChallengeSuccess(ChallengeSuccessDto payload) {
        log.info("Produce: board challenge success");
        kafkaTemplate.send("challenge-success", payload);
    }

    public void sendBoardAlertPosted(AlertMessageDto payload) {
        log.info("Produce: board posted alert");
        kafkaTemplate.send("alert-msg", payload);
    }

    public void sendBoardCreditAcquired(AddCreditDto payload) {
        log.info("Prodcue: credit acquired : {}", payload.getAmount());
        kafkaTemplate.send("add-credit", payload);
    }
}
