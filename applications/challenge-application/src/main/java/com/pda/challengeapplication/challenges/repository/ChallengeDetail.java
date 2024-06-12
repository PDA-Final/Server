package com.pda.challengeapplication.challenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ChallengeDetail")
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDetail {
    @Id
    Integer challengeId;
    String detailDescription;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "challengeId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    Challenge challenge;

    public void editChallengeDetail(String detailDescription, Challenge challenge) {
        this.detailDescription = detailDescription;
        this.challenge = challenge;
    }
}
