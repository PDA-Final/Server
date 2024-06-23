package com.pda.productapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Product")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "DTYPE")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "card_image")
    private String cardImg;

    @Column(name = "tags")
    private String tags;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "corp_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Corp corp;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Card card;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Saving saving;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Fund fund;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Loan loan;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private BoardCount boardCnt;

    protected Product() {
        super();
    }
}
