package com.pda.productapplication.repository;

import com.pda.productapplication.entity.Corp;
import com.pda.productapplication.entity.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    // 상품명과 회사명을 기준으로 필터링
    public static Specification<Product> equalProductName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Product> equalCorpName(String corpName) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Corp> corpJoin = root.join("corp", JoinType.INNER);
            return criteriaBuilder.equal(corpJoin.get("name"), corpName);
        };
    }
}
