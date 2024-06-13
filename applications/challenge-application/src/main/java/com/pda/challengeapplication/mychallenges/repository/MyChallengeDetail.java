package com.pda.challengeapplication.mychallenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "MyChallengeDetail")
@AllArgsConstructor
@NoArgsConstructor
public class MyChallengeDetail {
    @Id
    Integer myChallengeId;
    Integer inACNT;
    Integer outACNT;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "myChallengeId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    MyChallenge myChallenge;
}
