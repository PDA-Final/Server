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
@Table(name = "BoardCount")
@DiscriminatorValue("BoardCount")
public class BoardCount {
    @Id
    private Long productId;

    @Column(name = "board_count", nullable = false, columnDefinition = "int default 0")
    private int boardCount;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public void incrementBoardCount() {
        this.boardCount++;
    }
}
