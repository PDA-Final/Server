package com.pda.challengeapplication.challenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CorpChallengeDetail")
@AllArgsConstructor
@NoArgsConstructor
public class CorpChallengeDetail {
    @Id
    long challengeId;
    @Column(nullable = false)
    Long corpId;
    @Column(nullable = false)
    String corpName;
    @Column(nullable = false)
    String challengeUrl;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "challenge_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    Challenge challenge;

    public void editCorpChallengeDetail(String challengeUrl, Challenge challenge){
        this.challengeUrl = challengeUrl;
        this.challenge = challenge;
    }

}
