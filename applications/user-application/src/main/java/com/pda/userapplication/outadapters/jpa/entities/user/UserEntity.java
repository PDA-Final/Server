package com.pda.userapplication.outadapters.jpa.entities.user;

import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import com.pda.userapplication.outadapters.jpa.converter.JobConverter;
import com.pda.userapplication.outadapters.jpa.converter.UserRoleConverter;
import com.pda.userapplication.outadapters.jpa.entities.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "User")
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.MODULE)
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @Comment("유저 DB PK")
    private Long id;

    @Column(name = "tofin_id", unique = true, nullable = false, length = 20, updatable = false)
    @Comment("유저의 투핀 아이디")
    private String tofinId;

    @Column(name = "user_info", nullable = false, length = 100)
    @Comment("유저의 비밀번호")
    private String userInfo;

    @Column(name = "nickname", length = 20, nullable = false)
    @Comment("유저의 닉네임")
    private String nickname;

    @Column(name = "profile_img", length = 512, nullable = false)
    @ColumnDefault("'https://tofin-bucket.s3.ap-northeast-2.amazonaws.com/users/profile/default/user-icon1.svg'")
    @Comment("프로필 이미지")
    private String profileImage;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth", nullable = false) // 기업유저도 생일이 필요할까?
    @Comment("유저의 생일")
    private LocalDate birth;

    @CreatedDate
    @Column(name = "created_at")
    @Comment("회원가입일")
    private LocalDateTime createdAt;

    @Convert(converter = UserRoleConverter.class)
    @Column(name = "role", nullable = false, columnDefinition = "TINYINT(1) UNSIGNED")
    @Comment("유저 Role")
    private UserRole role;

    @ColumnDefault("'기타'")
    @Convert(converter = JobConverter.class)
    @Column(name = "job", nullable = false)
    @Comment("유저 직업")
    private Job job;

    @OneToOne(mappedBy = "user")
    private UserDetailEntity userDetail;
}
