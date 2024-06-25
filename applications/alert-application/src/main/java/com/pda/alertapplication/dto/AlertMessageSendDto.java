package com.pda.alertapplication.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@JsonSerialize
@JsonDeserialize
@Builder
@NoArgsConstructor // TODO
@AllArgsConstructor // TODO
public class AlertMessageSendDto {
    private Long id;
    private Long clientId;
    private String messageType; // FOLLOW, FIN, CREDIT
    private Long targetId; // 상대 유저 ID, 보드 ID, null
    private String content;
    private String thumbnail;
    private boolean isViewed;
    private LocalDateTime createdAt;
}
