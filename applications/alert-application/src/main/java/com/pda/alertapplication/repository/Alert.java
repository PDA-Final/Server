package com.pda.alertapplication.repository;

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

import java.time.LocalDateTime;

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

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "message_type", nullable = false)
    private String messageType;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "isViewed")
    private boolean isViewed; // false: unread, true: read

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}