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
@IdClass(BookmarkPk.class)
public class Bookmark extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Id
    @Column(name = "user_id")
    private long userId;
}
