package com.pda.productapplication.repository;

import com.pda.productapplication.entity.Card;
import com.pda.productapplication.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
