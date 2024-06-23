package com.pda.creditapplication.consume;

import com.pda.creditapplication.service.CreditService;
import com.pda.creditapplication.service.dto.req.SetAmountServiceRequest;
import com.pda.kafkautils.credit.AddCreditDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DepositListener {
    private final CreditService creditService;

    @KafkaListener(topics = "add-credit", concurrency = "3")
    public void deposit(AddCreditDto addCreditDto) {
        creditService.deposit(SetAmountServiceRequest.builder()
                .transactionDateTime(addCreditDto.getTransactionDateTime())
                .userId(addCreditDto.getUserId())
                .amount(addCreditDto.getAmount())
            .build());
    }
}
