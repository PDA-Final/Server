package com.pda.userapplication.outadapters.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "UserDetail")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "public_options", length = 4)
    @Comment("자산 공개 여부")
    private String publicOptions;

    @Column(name = "tendency", length = 20)
    @Comment("유저 성향")
    private String tendency;

    @Column(name = "social_name", length = 40)
    @Comment("유저 실명")
    private String socialName;

    @Column(name = "back_social_id", length = 30)
    @Comment("유저 주민등록번호")
    private String backSocialId;

    @Column(name = "contact", unique = true, length = 20)
    @Comment("유저 전화번호")
    private String contact;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;
}