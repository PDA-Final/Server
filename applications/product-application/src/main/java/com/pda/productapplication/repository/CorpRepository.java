package com.pda.productapplication.repository;

import com.pda.productapplication.entity.Corp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpRepository extends JpaRepository<Corp, Long> {
}
