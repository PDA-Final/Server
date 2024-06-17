package com.pda.productapplication.repository;

import com.pda.productapplication.entity.Card;
import com.pda.productapplication.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Long> {
}
