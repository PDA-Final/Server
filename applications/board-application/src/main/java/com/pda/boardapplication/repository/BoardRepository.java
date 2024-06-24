package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    public Page<Board> findByCategoryId(Pageable pageable, int categoryId);

    public Page<Board> findByUserId(Pageable pageable, long userId);

    public Page<Board> findByTitleContains(Pageable pageable, String keyword);

    public Page<Board> findByCategoryIdAndTitleContains(Pageable pageable, int categoryId, String title);
}
