package com.pda.challengeapplication.challenges.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    long id;
    @NotNull
    int challengeType;
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    String logoUrl;
    @NotNull
    LocalDate startAt;
    @NotNull
    LocalDate endAt;
    @NotNull
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



