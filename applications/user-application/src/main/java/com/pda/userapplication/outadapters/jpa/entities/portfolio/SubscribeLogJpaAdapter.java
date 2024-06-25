package com.pda.userapplication.outadapters.jpa.entities.portfolio;

import com.pda.userapplication.domains.PortfolioSubscribeLog;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.outadapters.jpa.entities.user.UserEntity;
import com.pda.userapplication.services.out.PortfolioSubscribeOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeLogJpaAdapter implements PortfolioSubscribeOutputPort {
    private final SubscribeLogRepository subscribeLogRepository;

    @Override
    public PortfolioSubscribeLog subscribe(UserId fromUserId, UserId toUserId) {
        return toDomain(subscribeLogRepository.save(SubscribeLogEntity.builder()
                .fromUser(UserEntity.builder().id(fromUserId.toLong()).build())
                .toUser(UserEntity.builder().id(toUserId.toLong()).build())
                .period(5) // 5주로 통일이다.
            .build()), fromUserId, toUserId);
    }

    @Override
    public List<PortfolioSubscribeLog> findSubscribeLogsBy(UserId fromUserId, UserId toUserId) {
        return subscribeLogRepository.findByFromUserAndToUserOrderByIdDesc(UserEntity.builder().id(fromUserId.toLong()).build(),
            UserEntity.builder().id(toUserId.toLong()).build())
            .stream().map(sub -> toDomain(sub, fromUserId, toUserId))
            .toList();
    }

    private PortfolioSubscribeLog toDomain(final SubscribeLogEntity subscribeLogEntity, UserId fromUserId, UserId toUserId) {
        return PortfolioSubscribeLog.builder()
            .id(subscribeLogEntity.getId())
            .period(subscribeLogEntity.getPeriod())
            .startAt(subscribeLogEntity.getCreatedAt())
            .fromUserId(fromUserId)
            .toUserId(toUserId)
            .build();
    }
}
