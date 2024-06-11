package com.pda.boardapplication.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardChallengeTag extends BaseEntity {

    @EmbeddedId
    private BoardChallengeTagPK boardChallengeTagPK;
}
