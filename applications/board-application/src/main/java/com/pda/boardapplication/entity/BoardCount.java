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
public class BoardCount extends BaseEntity {

    @Id
    private long boardId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private int likeCnt;

    private int viewCnt;
}
