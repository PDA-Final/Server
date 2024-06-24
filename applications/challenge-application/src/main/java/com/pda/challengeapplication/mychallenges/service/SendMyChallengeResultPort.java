package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.mychallenges.dto.request.outer.SendChallengeResultRequest;

public interface SendMyChallengeResultPort {
    void sendChallengeResult(SendChallengeResultRequest sendChallengeResultRequest);
}
