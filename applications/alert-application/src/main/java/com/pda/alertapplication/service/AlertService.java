package com.pda.alertapplication.service;

import com.pda.alertapplication.dto.AlertMessageSendDto;
import com.pda.alertapplication.repository.Alert;
import com.pda.alertapplication.repository.AlertRepository;
import com.pda.kafkautils.alert.AlertMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "alert-msg")
@RequiredArgsConstructor
@Transactional
public class AlertService {
    private final AlertRepository alertRepository;

    @Transactional
    public void insertAlert(Alert alert) {
        alertRepository.save(alert);
    }

    @Transactional
    public void deleteOldestAlertByUserId(Long userId, int delCount) {
        List<Alert> alerts = alertRepository.findOldestAlertsByUserId(userId, PageRequest.of(0, delCount));
        alertRepository.deleteAll(alerts);
    }

    @Transactional
    public int countAlertByUserId(Long userId) {
        return alertRepository.countAlertsByUserId(userId);
    }

    @Transactional
    public List<AlertMessageSendDto> selectMessagesByUserId(Long userId) { // TODO
        return alertRepository.findAlertsByUserId(userId)
                .stream()
                .map(alert -> AlertMessageSendDto.builder()
                        .clientId(alert.getClientId())
                        .content(alert.getContent())
                        .messageType(alert.getMessageType())
                        .thumbnail(alert.getMessageType())
                        .targetId(alert.getTargetId())
                        .isViewed(false)
                        .createdAt(alert.getCreatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    @Transactional
    public void updateRead(Long alertId) {
        alertRepository.markAsRead(alertId);
    }

    public Long selectProductIdByAlertId(Long alertId) {
        return alertRepository.findTargetIdByAlertId(alertId);
    }

}
