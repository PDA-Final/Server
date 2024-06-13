package com.pda.challengeapplication.mychallenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "MyEmoChallenge")
@AllArgsConstructor
@NoArgsConstructor
public class MyEmoChallenge {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    Integer emojiId;
    @ManyToOne
    @JoinColumn(name = "myChallengeId")
    MyChallenge myChallenge;
    Timestamp savingAt;
}
