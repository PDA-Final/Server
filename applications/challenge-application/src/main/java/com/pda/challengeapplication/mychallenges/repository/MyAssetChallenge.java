package com.pda.challengeapplication.mychallenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "MyAssetChallenge")
@AllArgsConstructor
@NoArgsConstructor
public class MyAssetChallenge {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    Long myChallengeId;
    long emojiId;
    LocalDate savingAt;
    boolean isSuccess;  // 절약챌린지의 경우 5일 전부 성공해야
}
