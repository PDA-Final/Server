package com.pda.alertapplication.repository;

import com.pda.kafkautils.alert.AlertMessageType;
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

@Entity
@NoArgsConstructor
@Table(name = "Alert")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", unique = true) // TODO
    private Long userId;

    @Column(name = "message_type", nullable = false)
    private String messageType;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;
}