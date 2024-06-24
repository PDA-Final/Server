package com.pda.kafkautils.board;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pda.kafkautils.KafkaJson;
import lombok.*;

@Getter
@JsonSerialize
@JsonDeserialize
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardPostSuccessDto implements KafkaJson {
    private Long boardId;
    private Long userId;
    private Long challengeId;
}
