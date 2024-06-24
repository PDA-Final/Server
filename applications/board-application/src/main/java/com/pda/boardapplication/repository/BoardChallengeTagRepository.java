package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.BoardChallengeTag;
import com.pda.boardapplication.entity.BoardChallengeTagPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardChallengeTagRepository extends JpaRepository<BoardChallengeTag, BoardChallengeTagPK> {
}
