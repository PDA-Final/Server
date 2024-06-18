package com.pda.challengeapplication.mychallenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "MyBoardChallenge")
@AllArgsConstructor
@NoArgsConstructor
public class MyBoardChallenge {
    @Id
    long myChallengeId;
    long boardId;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "myChallengeId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    MyChallenge myChallenge;
}
