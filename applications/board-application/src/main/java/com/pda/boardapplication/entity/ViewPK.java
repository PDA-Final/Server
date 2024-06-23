package com.pda.boardapplication.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ViewPK implements Serializable {

    private Board board;

    private long userId;
}
