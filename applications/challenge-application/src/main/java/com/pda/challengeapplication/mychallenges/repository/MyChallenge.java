package com.pda.challengeapplication.mychallenges.repository;

import com.pda.challengeapplication.challenges.repository.Challenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "MyChallenge")
@AllArgsConstructor
@NoArgsConstructor
public class MyChallenge {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn(name = "challengeId")
    Challenge challenge;
    long userId;
    LocalDate startAt;
    LocalDate endAt;
    String status;



    public void editMyChallengeStatus(String status) {
        this.status = status;
    }

}
