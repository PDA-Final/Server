package com.pda.challengeapplication.challenges.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ModifyChallengeRequest {
    String name;
    String description;
    String logoUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Timestamp endAt;
    String detailDescription;
}
