package com.pda.boardapplication.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class Like extends BaseEntity {

    @EmbeddedId
    private LikePK likePK;
}
