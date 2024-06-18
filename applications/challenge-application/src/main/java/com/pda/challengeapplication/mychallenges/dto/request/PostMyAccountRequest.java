package com.pda.challengeapplication.mychallenges.dto.request;

import com.pda.challengeapplication.mychallenges.repository.MyAssetChallengeDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMyAccountRequest {

    long myChallengeId;
    long inACNT;
    long outACNT;

    public MyAssetChallengeDetail convertToAccountEntity() {
        return new MyAssetChallengeDetail(myChallengeId, inACNT,outACNT);
    }
}
