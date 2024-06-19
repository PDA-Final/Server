package com.pda.creditapplication.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor
@Table(name = "UserCredit")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class CreditStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "amount", columnDefinition = "bigint unsigned default 0 not null")
    private Long amount;

    public void withdraw(Long amount) {
        if (isZeroOrMinus(amount))
            throw new IllegalArgumentException("Amount can't be negative");
        if (this.amount < amount)
            throw new IllegalArgumentException("Amount must be greater than or equal to amount");

        this.amount = this.amount - amount;
    }

    public void deposit(Long amount) {
        if (isZeroOrMinus(amount))
            throw new IllegalArgumentException("Amount can't be negative");
        this.amount += amount;
    }

    private boolean isZeroOrMinus(Long amount) {
        return amount <= 0;
    }
}
