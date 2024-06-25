package com.pda.alertapplication.repository;

import com.pda.kafkautils.alert.AlertMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public interface AlertRepository extends JpaRepository<Alert, Long> {
    // TODO 쿼리 수정

    List<Alert> findAlertsByClientId(Long clientId);

    @Query("SELECT COUNT(a) FROM Alert a WHERE a.clientId = :clientId AND a.isViewed = false")
    int countUnviewAlertsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT a FROM Alert a WHERE a.clientId = :clientId ORDER BY a.createdAt ASC")
    List<Alert> findOldestAlertsByUserId(@Param("clientId") Long clientId, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Alert a WHERE a.clientId = :clientId")
    int countAlertsByUserId(@Param("clientId") Long clientId);

    @Modifying
    @Query("UPDATE Alert a SET a.isViewed = true WHERE a.id = :alertId")
    void markAsView(@Param("alertId") Long alertId);

    @Modifying
    @Query("UPDATE Alert a SET a.isViewed = true WHERE a.clientId = :clientId")
    void markAllAsViewByClientId(@Param("clientId") Long clientId);

    @Modifying
    @Query("DELETE FROM Alert a WHERE a.clientId = :clientId")
    void deleteAllByClientId(@Param("clientId") Long clientId);

    @Query("SELECT a.targetId FROM Alert a WHERE a.id = :alertId")
    Long findTargetIdByAlertId(@Param("alertId") Long alertId);
}
