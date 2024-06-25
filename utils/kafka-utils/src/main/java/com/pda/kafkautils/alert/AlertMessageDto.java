package com.pda.kafkautils.alert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pda.kafkautils.KafkaJson;
import lombok.*;

@Getter
@JsonSerialize
@JsonDeserialize
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertMessageDto implements KafkaJson {
    private Long clientId;
    private String messageType; // FOLLOW, FIN, CREDIT
    private Long targetId; // 상대 유저 ID, 보드 ID, null
    private String content;
    private String thumbnail;
}
