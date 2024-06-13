package com.pda.productapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Author {
    @Column(name = "author_nickname", nullable = false)
    private String authorNickname;

    @Column(name = "author_profile", nullable = false)
    private String authorProfile;

    @Column(name = "author_type", nullable = false)
    private String authorType;
}
