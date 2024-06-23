package com.pda.alertapplication.consume;

import com.pda.alertapplication.service.AlertService;
import com.pda.kafkautils.alert.AlertMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AlertMessageListener {
    private final AlertService alertService;

    @KafkaListener(
            topics = "alert-topic",
            concurrency = "2"
    )
    public void alertMessage(AlertMessageDto alertMessageDto) { // TODO
        System.out.println(alertMessageDto.getContent());
        System.out.println(alertMessageDto.getThumbnail());
    }

}