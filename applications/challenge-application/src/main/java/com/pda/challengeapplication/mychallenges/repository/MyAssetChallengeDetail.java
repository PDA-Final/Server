package com.pda.challengeapplication.mychallenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "MyAssetChallengeDetail")
@AllArgsConstructor
@NoArgsConstructor
public class MyAssetChallengeDetail {
    @Id
    long myChallengeId;
    long inACNT;
    long outACNT;

}
