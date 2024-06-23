package com.pda.challengeapplication.challenges.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "ChallengeDetail")
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDetail {
    @Id
    long challengeId;
    @NotNull
    String detailDescription;
    Integer standardNum;
    String standardCg;
    String badgeName;
    Integer reward;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "challengeId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    Challenge challenge;

    public void editChallengeDetail(String detailDescription, Challenge challenge) {
        this.detailDescription = detailDescription;
        this.challenge = challenge;
    }
}
