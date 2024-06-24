package com.pda.alertapplication.service;

import com.pda.kafkautils.alert.AlertMessageReqDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SseService {
    private final Map<String, SseEmitter> clients = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String clientId) { // TODO : SseEmitter는 Long id 말고 String 들어가야 함.
        SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> clients.remove(clientId));
        emitter.onTimeout(() -> clients.remove(clientId));
        clients.put(clientId, emitter);
        return emitter;
    }

    public void sendAlert(String clientId, AlertMessageReqDto alertMessageReqDto) {
        SseEmitter emitter = clients.get(clientId);
        if (emitter != null) {
            try {
                emitter.send(alertMessageReqDto);
            } catch (IOException e) {
                emitter.completeWithError(e);
                clients.remove(clientId);
            }
        }
    }

    public void sendAlertToAll(AlertMessageReqDto alertMessageReqDto) {
        clients.forEach((clientId, emitter) -> {
            try {
                emitter.send(alertMessageReqDto);
            } catch (IOException e) {
                emitter.completeWithError(e);
                clients.remove(clientId);
            }
        });
    }
}
