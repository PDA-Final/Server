package com.pda.boardapplication.repository;

import com.pda.boardapplication.entity.BoardCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCountRepository extends JpaRepository<BoardCount, Long> {
}
