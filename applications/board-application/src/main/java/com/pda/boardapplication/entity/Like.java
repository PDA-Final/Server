package com.pda.boardapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"like\"")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LikePK.class)
public class Like extends BaseEntity {


    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_id")
    private Board board;

    @Id
    @Column(name = "user_id")
    private long userId;
}
