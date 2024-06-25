package com.pda.userapplication.outadapters.jpa.entities.portfolio;

import com.pda.userapplication.outadapters.jpa.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeLogRepository extends JpaRepository<SubscribeLogEntity, Long> {
    List<SubscribeLogEntity> findByFromUserAndToUserOrderByIdDesc(UserEntity fromUser, UserEntity toUser);
}
