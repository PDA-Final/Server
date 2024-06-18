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
@Table(name = "Loan")
@DiscriminatorValue("Loan")
public class Loan {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "min_interest_rate", nullable = false)
    private float minInterestRate;

    @Column(name = "max_interest_rate", nullable = false)
    private float maxInterestRate;

    @Column(name = "max_loan_amount", nullable = false)
    private String maxLoanAmount;

    @Column(name = "description", nullable = false)
    private String description;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
}
