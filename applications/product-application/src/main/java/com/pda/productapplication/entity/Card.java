package com.pda.productapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Card")
@DiscriminatorValue("Card")
public class Card {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "notice")
    private String notice;

    @Column(name = "annual_fee", nullable = false)
    private String annualFee;

    @Column(name = "rewards")
    private String rewards;

    @Column(name = "base_record", nullable = false)
    private String baseRecord;

    @Column(name = "main_benefit", nullable = false)
    private String mainBenefit;

    @Column(name = "sub_benefit", nullable = false)
    private String subBenefit;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "terms", nullable = false)
    private String terms;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
}
