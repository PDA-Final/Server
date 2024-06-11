package com.pda.userapplication.outadapters.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshInfoRepository extends CrudRepository<RefreshInfoEntity, String> {
    Optional<RefreshInfoEntity> findByRefreshToken(String refreshToken);
}
