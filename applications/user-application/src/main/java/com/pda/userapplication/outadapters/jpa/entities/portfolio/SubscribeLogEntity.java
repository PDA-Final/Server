package com.pda.userapplication.outadapters.jpa.entities.portfolio;

import com.pda.userapplication.outadapters.jpa.entities.BaseTimeEntity;
import com.pda.userapplication.outadapters.jpa.entities.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SubscribeLog")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class SubscribeLogEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ColumnDefault("5")
    @Comment("구독 기간(주단위 5주 통일)")
    @Column(name = "period", nullable = false, updatable = false)
    private Integer period;

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity toUser;
}
