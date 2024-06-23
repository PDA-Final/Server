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
@Table(name = "Saving")
@DiscriminatorValue("Saving")
public class Saving {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "interest_rate", nullable = false)
    private float interestRate;

    @Column(name = "prime_interest_rate", nullable = false)
    private float primeInterestRate;

    @Column(name = "saving_term", nullable = false)
    private String savingTerm;

    @Column(name = "special_offer_summary")
    private String specialOfferSummary;

    @Column(name = "special_offer_period")
    private String specialOfferPeriod;

    @Column(name = "join_period", nullable = false, columnDefinition = "TEXT")
    private String joinPeriod;

    @Column(name = "join_amount", nullable = false, columnDefinition = "TEXT")
    private String joinAmount;

    @Column(name = "join_target", nullable = false, columnDefinition = "TEXT")
    private String joinTarget;

    @Column(name = "special_conditions", nullable = false)
    private String specialConditions;

    @Column(name = "channel", nullable = false)
    private String channel;

    @Column(name = "interest_payment_cycle", nullable = false, columnDefinition = "TEXT")
    private String interestPaymentCycle;

    @Column(name = "note", nullable = false, columnDefinition = "TEXT")
    private String note;

    @Column(name = "depositor_protection", nullable = false)
    private String depositorProtection;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
}
