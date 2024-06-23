package com.pda.productapplication.repository;

import com.pda.productapplication.entity.Card;
import com.pda.productapplication.entity.Saving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingRepository extends JpaRepository<Saving, Long> {
}
