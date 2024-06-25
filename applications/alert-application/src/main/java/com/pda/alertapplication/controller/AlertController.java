package com.pda.alertapplication.controller;

import com.pda.alertapplication.service.AlertService;
import com.pda.alertapplication.service.EmitterService;
import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@Tag(name = "[Alert]", description = "Alert API")
@RequestMapping(value = "/alerts")
public class AlertController {

    private final EmitterService emitterService;
    private final AlertService alertService;

    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    @Operation(summary = "SSE Subscribe", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Success!")
    public SseEmitter subscribe(
            @AuthUser AuthUserInfo clientId,
            @RequestHeader (value = "Last-Event-ID", required = false) String lastEventId) {
        return emitterService.addEmitter(clientId.getId(), lastEventId);
    }
}
