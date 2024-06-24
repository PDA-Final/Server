package com.pda.challengeapplication.configs.kafka;

import com.pda.challengeapplication.mychallenges.dto.request.outer.SendChallengeResultRequest;
import com.pda.challengeapplication.mychallenges.service.SendMyChallengeResultPort;
import com.pda.kafkautils.KafkaJson;
import com.pda.kafkautils.challenge.ChallengeResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOutputAdapter implements SendMyChallengeResultPort {
    private final KafkaTemplate<String, KafkaJson> kafkaTemplate;

    @Async
    @Override
    public void sendChallengeResult(final SendChallengeResultRequest request){
        kafkaTemplate.send("alret-topic", ChallengeResultDto.builder()
                .result(request.getResult())
                .challengeName(request.getChallengeName())
                .logoUrl(request.getLogoUrl())
                .userId(request.getUserId())
                .transactionDateTime(request.getTransactionDateTime())
                .build());
    }
}
