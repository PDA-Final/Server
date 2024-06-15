package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Like;
import com.pda.boardapplication.entity.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, LikePK> {
}
