package com.pda.boardapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    private String content;

    private String authorNickname;

    private int authorType;

    private String authorProfile;

    @OneToMany(mappedBy = "board")
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "likePK.board")
    @JsonBackReference
    private List<Like> likes;

    @OneToMany(mappedBy = "viewPK.board")
    @JsonBackReference
    private List<View> views;

    @OneToMany(mappedBy = "boardChallengeTagPK.board")
    @JsonBackReference
    private List<BoardChallengeTag> taggedChallenges;

    @OneToMany(mappedBy = "boardProductTagPK.board")
    @JsonBackReference
    private List<BoardProductTag> taggedProducts;

    public void updateEntity(String title, String content) {
        this.title = title == null ? this.title : title;
        this.content = content == null ? this.content : content;
    }
}
