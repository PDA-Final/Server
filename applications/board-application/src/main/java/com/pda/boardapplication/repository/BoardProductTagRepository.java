package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.BoardProductTag;
import com.pda.boardapplication.entity.BoardProductTagPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardProductTagRepository extends JpaRepository<BoardProductTag, BoardProductTagPK> {
    Page<BoardProductTag> findByProductId(Pageable pageable, long productId);
}
