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
@Table(name = "board_count")
@DiscriminatorValue("board_count")
public class BoardCount {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "board_cnt", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int boardCnt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public void incrementBoardCount() {
        this.boardCnt++;
    }
}
