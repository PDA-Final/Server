package com.pda.kafkautils.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pda.kafkautils.KafkaJson;
import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonSerialize
@JsonDeserialize
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// userId를 제외한 필드들이 null이 될 수 있음
public class UserUpdateDto implements KafkaJson {
    private Long userId;
    private String profileImage;
    private String nickname;
    private UserRole role;
    private Job job;
}
