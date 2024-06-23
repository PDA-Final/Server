package com.pda.challengeapplication.challenges.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "Challenge")
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer challengeType;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    String logoUrl;
    @Column(nullable = false)
    LocalDate startAt;
    @Column(nullable = false)
    LocalDate endAt;
    Integer term;

    @OneToOne(mappedBy = "challenge")
    ChallengeDetail challengeDetail;

    @OneToOne(mappedBy = "challenge")
    CorpChallengeDetail corpChallengeDetail;

    public void editChallenge(String name, String description, String logoUrl, LocalDate startAt, LocalDate endAt) {
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.startAt = startAt;
        this.endAt = endAt;
    }


}



