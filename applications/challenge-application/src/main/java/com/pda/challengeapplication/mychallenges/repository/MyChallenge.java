package com.pda.challengeapplication.mychallenges.repository;

import com.pda.challengeapplication.challenges.repository.Challenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "MyChallenge")
@AllArgsConstructor
@NoArgsConstructor
public class MyChallenge {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "challengeID")
    Challenge challenge;
    Long userId;
    Timestamp startAt;
    Timestamp EndAt;
    String status;
}
