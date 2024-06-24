package com.pda.kafkautils.alert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pda.kafkautils.KafkaJson;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@JsonSerialize
@JsonDeserialize
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertMessageRespDto implements KafkaJson {
    private Long clientId;
    private String messageType; // FOLLOW, CREDIT, FIN
    private Long targetId; // 팔로우 -> 팔로우 한 사람의 ID, 크레딧 보유도 (중복), 보드 ID
    private String content;
    private String thumbnail;
    private boolean isViewed;
    private LocalDateTime createdAt;
}
