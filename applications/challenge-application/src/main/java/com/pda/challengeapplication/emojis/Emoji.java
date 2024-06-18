package com.pda.challengeapplication.emojis;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Emoji")
@AllArgsConstructor
@NoArgsConstructor
public class Emoji {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    @NotNull
    String emotion;
    @NotNull
    String imgUrl;
    @NotNull
    int price;

}
