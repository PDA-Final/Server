package com.pda.boardapplication.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class BookmarkPk implements Serializable {

    private long board;

    private long userId;
}
