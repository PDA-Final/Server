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
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "board_id")
    private Board board;

    private long user_id;

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    private String content;

    private String authorNickname;

    private String authorProfile;

    private String authorType;
}
