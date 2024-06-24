package com.pda.boardapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(columnDefinition = "TEXT")
    private String content;

    private String summary;

    private String thumbnail;

    private String authorNickname;

    private int authorType;

    private String authorProfile;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<Like> likes;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<View> views;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<BoardChallengeTag> taggedChallenges;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<BoardProductTag> taggedProducts;

    @OneToOne(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private BoardCount boardCount;

    public void updateEntity(String title, String thumbnail, String summary, String content) {
        this.title = title == null ? this.title : title;
        this.thumbnail = thumbnail == null ? this.thumbnail : thumbnail;
        this.summary = summary == null ? this.summary : summary;
        this.content = content == null ? this.content : content;
    }
}
