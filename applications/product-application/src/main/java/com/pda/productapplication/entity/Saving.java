package com.pda.productapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Saving")
@DiscriminatorValue("Saving")
public class Saving extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "interest_rate", nullable = false)
    private float interestRate;

    @Column(name = "prime_interest_rate", nullable = false)
    private float primeInterestRate;

    @Column(name = "saving_term", nullable = false)
    private String savingTerm;

    @Column(name = "special_offer_summary", nullable = false)
    private String specialOfferSummary;

    @Column(name = "special_offer_period", nullable = false)
    private String specialOfferPeriod;

    @Column(name = "join_period", nullable = false)
    private String joinPeriod;

    @Column(name = "join_amount", nullable = false)
    private String joinAmount;

    @Column(name = "join_target", nullable = false)
    private String joinTarget;

    @Column(name = "special_conditions", nullable = false)
    private String specialConditions;

    @Column(name = "channel", nullable = false)
    private String channel;

    @Column(name = "interest_payment_cycle", nullable = false)
    private String interestPaymentCycle;

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "depositor_protection", nullable = false)
    private String depositorProtection;

    @Column(name = "updated_at") // TODO
    private String updatedAt;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
}
