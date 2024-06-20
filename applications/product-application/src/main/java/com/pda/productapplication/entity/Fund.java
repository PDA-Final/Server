package com.pda.productapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Fund")
@DiscriminatorValue("Fund")
public class Fund {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "fund_code", nullable = false)
    private String fundCode;

    @Column(name = "std_price", nullable = false)
    private float stdPrice;

    @Column(name = "diff_price", nullable = false)
    private float diffPrice;

    @Column(name = "drv_nav", nullable = false)
    private float drvNav;

    @Column(name = "rt_1m")
    private float rt1m;

    @Column(name = "rt_3m")
    private float rt3m;

    @Column(name = "rt_6m")
    private float rt6m;

    @Column(name = "rt_ytd")
    private float rtYtd;

    @Column(name = "rt_1y")
    private float rt1y;

    @Column(name = "rt_3y")
    private float rt3y;

    @Column(name = "rt_5y")
    private float rt5y;

    @Column(name = "ter")
    private float ter;

    @Column(name = "risk_grade", nullable = false)
    private int riskGrade;

    @Column(name = "risk_grade_text", nullable = false)
    private String riskGradeText;

    @Column(name = "fee_gb", nullable = false)
    private String feeGb;

    @Column(name = "category1", nullable = false)
    private String category1;

    @Column(name = "category2", nullable = false)
    private String category2;

    @Column(name = "info_object", columnDefinition = "TEXT")
    private String infoObject;

    @Column(name = "info_strategy", columnDefinition = "TEXT")
    private String infoStrategy;

    @Column(name = "region")
    private String region;

    @Column(name = "amt_gb", nullable = false)
    private String amtGb;

    @Column(name = "exce_bm", nullable = false)
    private String exceBm;

    @Column(name = "risk_gb", nullable = false)
    private String riskGb;

    @Column(name = "rt_gb", nullable = false)
    private String rtGb;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
}
