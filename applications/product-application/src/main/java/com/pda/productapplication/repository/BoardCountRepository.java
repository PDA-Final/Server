package com.pda.productapplication.repository;

import com.pda.productapplication.entity.BoardCount;
import com.pda.productapplication.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCountRepository extends JpaRepository<BoardCount, Long> {
}
