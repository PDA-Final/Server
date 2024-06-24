package com.pda.alertapplication.controller;

import com.pda.alertapplication.service.AlertService;
import com.pda.alertapplication.service.EmitterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@Tag(name = "[Alert]", description = "Alert API")
@RequestMapping(value = "/alerts")
public class AlertController {

    private final EmitterService emitterService;
    private final AlertService alertService;

    @GetMapping(value = "/subscribe/{clientId}", produces = "text/event-stream")
    public SseEmitter subscribe(
            @PathVariable Long clientId,
            @PathVariable (value = "Last-Event-ID", required = false) String lastEventId) {
        return emitterService.addEmitter(clientId, lastEventId);
    }

    // 알림 몇 개 안 읽었는지 알려주는
    // 알림 페이지 열면 안 읽은 알림 0개됨

    // 1. 구독
    // 2. 카프카 오면 alert->client 로 전송: 메세지 온 거 다 보내고
    // 3. clien는 특정 이벤트 발생하면 알림 서버에게 읽었따고 전송
}