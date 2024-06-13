package com.pda.productapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Card")
@DiscriminatorValue("Card")
public class Card extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_image", nullable = false)
    private String cardImgUrl;

    @Column(name = "notice", length = 50)
    private String notice;

    @Column(name = "annual_fee", nullable = false, length = 30)
    private String annualFee;

    @Column(name = "rewards", length = 30)
    private String rewards;

    @Column(name = "base_record", nullable = false, length = 30)
    private String baseRecord;

    @Column(name = "main_benefit", nullable = false, length = 30)
    private String mainBenefit;

    @Column(name = "sub_benefit", nullable = false, length = 30)
    private String subBenefit;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "terms", nullable = false)
    private String terms;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
}
