package com.pda.productapplication.repository;

import com.pda.productapplication.entity.BoardCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCountRepository extends JpaRepository<BoardCount, Long> {
}
