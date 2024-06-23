package com.pda.creditapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditStoreRepository extends JpaRepository<CreditStore, Long> {
    Optional<CreditStore> findByUserId(Long userId);
}
