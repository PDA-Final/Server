package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Like;
import com.pda.boardapplication.entity.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, LikePK> {

    List<Like> findAllByBoardId(long boardId);
}
