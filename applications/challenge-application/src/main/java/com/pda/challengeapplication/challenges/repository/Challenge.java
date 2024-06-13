package com.pda.challengeapplication.challenges.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Challenge")
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private int challengeType;
    @NotNull
    private String name;
    private int participants;
    @NotNull
    private String description;
    @NotNull
    private String logoUrl;
    private Timestamp startAt;
    private Timestamp endAt;
    private int term;


    public void editChallenge(String name, String description, String logoUrl, Timestamp startAt, Timestamp endAt) {
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.startAt = startAt;
        this.endAt = endAt;
    }




}



