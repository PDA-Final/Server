package com.pda.alertapplication.controller;

import com.pda.alertapplication.dto.AlertMessageSendDto;
import com.pda.alertapplication.repository.Alert;
import com.pda.alertapplication.service.AlertService;
import com.pda.alertapplication.service.EmitterService;
import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "[Alert]", description = "Alert API")
@RequestMapping(value = "/alerts")
public class AlertController {

    private final EmitterService emitterService;
    private final AlertService alertService;

    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    @Operation(summary = "SSE Subscribe", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Success!")
    public SseEmitter subscribe(
            @AuthUser AuthUserInfo authUserInfo,
            @RequestHeader (value = "Last-Event-ID", required = false) String lastEventId) {
        return emitterService.addEmitter(authUserInfo.getId(), lastEventId);
    }

    @GetMapping
    @Operation(summary = "Get Alerts", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public GlobalResponse<Object> getAlerts(
            @AuthUser AuthUserInfo authUserInfo
    ) {
        log.debug("알림 리스트 조회");
        Map<String, Object> result = new HashMap<>();

        Long userId = authUserInfo.getId();

        List<AlertMessageSendDto> alerts = alertService.getAlertsByClientId(userId);
        result.put("alerts", alerts);

        return ApiUtils.success("success", result);
    }

    @GetMapping("/{alertId}")
    @Operation(summary = "Get Alert", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public GlobalResponse<Object> getAlert(
            @PathVariable("alertId") Long alertId
    ) {
        log.debug("Alert id: {}", alertId);
        Map<String, Object> result = new HashMap<>();

        AlertMessageSendDto alertMessageSendDto
                = alertService.getAlert(alertId);
        result.put("alert", alertMessageSendDto);

        return ApiUtils.success("success", result);
    }

    @PutMapping("/{alertId}/view")
    @Operation(summary = "Alert marked as view", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public GlobalResponse<Object> markAlertAsView(
            @PathVariable("alertId") Long alertId
    ) {
        log.debug("알림 읽음 처리 요청: {}", alertId);
        Map<String, Object> result = new HashMap<>();

        alertService.updateRead(alertId);

        AlertMessageSendDto alertMessageSendDto = alertService.getAlert(alertId);
        result.put("alert", alertMessageSendDto);

        return ApiUtils.success("Alert marked as read", result);
    }
}
