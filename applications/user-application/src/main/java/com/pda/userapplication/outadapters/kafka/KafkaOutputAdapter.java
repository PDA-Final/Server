package com.pda.userapplication.outadapters.kafka;

import com.pda.kafkautils.KafkaJson;
import com.pda.kafkautils.credit.AddCreditDto;
import com.pda.userapplication.services.out.SendCreditOutputPort;
import com.pda.userapplication.services.out.dto.req.SendCreditOutputRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOutputAdapter implements SendCreditOutputPort {
    private final KafkaTemplate<String, KafkaJson> kafkaTemplate;

    @Override
    public void addCredit(final SendCreditOutputRequest request) {
        kafkaTemplate.send("add-credit", AddCreditDto.builder()
                .transactionDateTime(request.getTransactionDateTime())
                .amount(request.getAmount())
                .userId(request.getUserId())
            .build());
    }
}
