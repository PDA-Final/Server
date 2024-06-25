package com.pda.alertapplication.service;

import com.pda.alertapplication.repository.Alert;
import com.pda.alertapplication.repository.EmitterRepository;
import com.pda.kafkautils.alert.AlertMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmitterService {
    private final AlertService alertService;
    private final EmitterRepository emitterRepository;

    private static final int MAX_NOTIFICATIONS_COUNT = 6;
    public static final Long DEFAULT_TIMEOUT = 3600L * 1000;

    @KafkaListener(topics = "alert-msg", concurrency = "2")
    public void listen(AlertMessageDto alertMessageDto) {
        log.info("카프카에게 받은 메세지: {}", alertMessageDto);

        Long clientId = alertMessageDto.getClientId();
        Alert alert = Alert.builder()
                .clientId(alertMessageDto.getClientId())
                .content(alertMessageDto.getContent())
                .messageType(alertMessageDto.getMessageType())
                .thumbnail(alertMessageDto.getThumbnail())
                .targetId(alertMessageDto.getTargetId())
                .isViewed(false)
                .createdAt(LocalDateTime.now())
                .build();

        alertService.insertAlert(alert);
        log.info("알림 저장됨: {}", alert);
        int curCnt = alertService.countAlertByUserId(clientId);
        if (curCnt > MAX_NOTIFICATIONS_COUNT) {
            int delCount = curCnt - MAX_NOTIFICATIONS_COUNT;
            alertService.deleteOldestAlertByUserId(clientId, delCount);
        }

        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithById(String.valueOf(clientId));
        sseEmitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, alert);
                    sendToClient(emitter, key, alert);
                }
        );
    }

    private void sendToClient(SseEmitter emitter, String emitterId, Object data) { // TODO
        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .name("alert")
                    .data(data));
            log.info("Kafka로부터 전달 받은 메세지를 클라이언트에게 전송. emitterId : {}, message : {}", emitterId, data);
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
            log.error("메시지 전송 에러 : {}", e);
        }
    }

    public SseEmitter addEmitter(Long clientId, String lastEventId) {
        if (lastEventId == null) {
            lastEventId = "";
        }

        String emitterId = clientId + "_" + System.currentTimeMillis();
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

        log.info("emitterId : {} 사용자 emitter 연결 ", emitterId);

        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            emitterRepository.deleteById(emitterId);
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitterRepository.deleteById(emitterId);
        });

        sendToClient(emitter, emitterId, "connected!"); // 503 에러방지 더미 데이터

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithById(String.valueOf(clientId));
            String finalLastEventId = lastEventId;
            events.entrySet().stream()
                    .filter(entry -> finalLastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }
        return emitter;
    }

    @Scheduled(fixedRate = 180000) // 3분
    public void sendHeartbeat() {
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitters();
        sseEmitters.forEach((key, emitter) -> {
            try {
                emitter.send(SseEmitter.event().id(key).name("heartbeat").data(""));
                log.info("하트비트 메세지 전송");
            } catch (IOException e) {
                emitterRepository.deleteById(key);
                log.error("하트비트 전송 실패: {}", e.getMessage());
            }
        });
    }
}
