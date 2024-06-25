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
@IdClass(UnlockedPK.class)
public class Unlocked extends BaseEntity {

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_id")
    private Board board;

    @Id
    @Column(name = "user_id")
    private long userId;
}
