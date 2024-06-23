package com.pda.boardapplication.service;

import com.pda.boardapplication.entity.BoardCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCountRepository extends JpaRepository<BoardCount, Long> {
}
