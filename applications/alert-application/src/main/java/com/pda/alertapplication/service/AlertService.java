package com.pda.alertapplication.service;

import com.pda.alertapplication.dto.AlertMessageSendDto;
import com.pda.alertapplication.repository.Alert;
import com.pda.alertapplication.repository.AlertRepository;
import com.pda.exceptionhandler.exceptions.NotFoundException;
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

    public List<AlertMessageSendDto> getAlertsByClientId(Long clientId) {
        List<Alert> alerts = alertRepository.findAlertsByClientId(clientId);
        log.debug("알림 찾기. 알림 사이즈: {}, 유저 ID: {}", alerts.size(), clientId);
        return alerts.stream()
                .map(alert -> AlertMessageSendDto.builder()
                        .id(alert.getId())
                        .clientId(alert.getClientId())
                        .content(alert.getContent())
                        .messageType(alert.getMessageType())
                        .thumbnail(alert.getThumbnail())
                        .targetId(alert.getTargetId())
                        .isViewed(alert.isViewed())
                        .createdAt(alert.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public AlertMessageSendDto getAlert(Long alertId) {
        log.debug("Get alert: {}", alertId);

        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new NotFoundException("Alert not found with id: " + alertId));

        return AlertMessageSendDto.builder()
                .id(alert.getId())
                .clientId(alert.getClientId())
                .content(alert.getContent())
                .messageType(alert.getMessageType())
                .thumbnail(alert.getThumbnail())
                .targetId(alert.getTargetId())
                .isViewed(alert.isViewed())
                .createdAt(alert.getCreatedAt())
                .build();
    }

    @Transactional
    public void insertAlert(Alert alert) {
        log.debug("알림 넣기 전: {}", alert);
        alertRepository.save(alert);
        log.debug("알림 넣은 후: {}", alert);
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
        return alertRepository.findAlertsByClientId(userId)
                .stream()
                .map(alert -> AlertMessageSendDto.builder()
                        .id(alert.getId())
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
