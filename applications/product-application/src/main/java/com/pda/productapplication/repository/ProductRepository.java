package com.pda.productapplication.repository;

import com.pda.productapplication.entity.CardProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardProductRepository extends JpaRepository<CardProduct, Long> {

    Optional<CardProduct> findById(Long id);
}