package com.pda.challengeapplication.mychallenges.dto.request;

import com.pda.challengeapplication.mychallenges.repository.MyAssetChallenge;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyEmoLogRequest {
    @Id
    long id;
    long myChallengeId;
    long emojiId;
    LocalDate savingAt;

    public MyAssetChallenge convertToAccountEntity() {
        this.savingAt = LocalDate.now();
        MyAssetChallenge myAssetChallenge = new MyAssetChallenge(id, myChallengeId, emojiId, savingAt, true);
        return myAssetChallenge;
    }
}
