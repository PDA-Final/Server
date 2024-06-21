package com.pda.productapplication.repository;

import com.pda.productapplication.entity.Category;
import com.pda.productapplication.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product p ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Product> findRandomProducts(@Param("limit") int limit);

    Page<Product> findByNameLike(String name, Pageable pageable);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    // 최신순 정렬
    @Query(value = "SELECT * FROM product p WHERE p.category_id = :categoryId ORDER BY p.created_at DESC", nativeQuery = true)
    Page<Product> findByCategoryIdOrderByCreatedAt(@Param("categoryId") Long categoryId, Pageable pageable);

    // 인기순 정렬
    @Query("SELECT p FROM Product p JOIN p.boardCount b WHERE p.category.id = :categoryId ORDER BY b.boardCount DESC")
    Page<Product> findByCategoryIdOrderByBoardCount(@Param("categoryId") Long categoryId, Pageable pageable);
}
