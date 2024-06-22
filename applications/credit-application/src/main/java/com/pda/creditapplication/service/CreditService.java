package com.pda.creditapplication.service;

import com.pda.creditapplication.repository.CreditLog;
import com.pda.creditapplication.repository.CreditLogRepository;
import com.pda.creditapplication.repository.CreditStore;
import com.pda.creditapplication.repository.CreditStoreRepository;
import com.pda.creditapplication.service.dto.req.SetAmountServiceRequest;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreditService {
    private final CreditStoreRepository storeRepository;
    private final CreditLogRepository logRepository;

    @Async
    @Transactional
    public void deposit(final SetAmountServiceRequest request) {
        Optional<CreditStore> optionalCreditStore = storeRepository.findByUserId(request.getUserId());

        if (optionalCreditStore.isPresent()) {
            CreditStore creditStore = optionalCreditStore.get();
            creditStore.deposit(request.getAmount());
            storeRepository.save(creditStore);
            creditLogging(creditStore, request.getAmount(), request.getTransactionDateTime());
            return;
        }

        CreditStore creditStore = storeRepository.save(CreditStore.builder()
                .amount(request.getAmount())
                .userId(request.getUserId())
            .build());

        creditLogging(creditStore, request.getAmount(), request.getTransactionDateTime());
    }

    @Transactional
    public void withdraw(final SetAmountServiceRequest request) {
        try {
            CreditStore creditStore = storeRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new BadRequestException("유저를 찾지 못함"));

            creditStore.withdraw(request.getAmount());
            creditLogging(creditStore, -request.getAmount(), request.getTransactionDateTime());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("가진 크레딧 보다 더 많이 인출할 수 없습니다.");
        }
    }

    public void getAmount(Long userId) {

    }

    public void getLogs(Long userId) {

    }

    private CreditLog creditLogging(CreditStore creditStore, Long amount, LocalDateTime transactionDateTime) {
        return logRepository.save(CreditLog.builder()
            .creditStore(creditStore)
            .transactionAmount(amount)
            .transactionDateTime(transactionDateTime)
            .creditStore(creditStore)
            .build());
    }
}
