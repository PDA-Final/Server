package com.pda.boardapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BoardChallengeTagPK.class)
public class BoardChallengeTag extends BaseEntity {

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_id")
    private Board board;

    @Id
    @Column(name = "challenge_id")
    private long challengeId;
}
