package com.pda.userapplication.outadapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity userEntity);
    boolean existsByTofinId(String tofinId);
    Optional<UserEntity> findByTofinId(String tofinId);
    Optional<UserEntity> findById(Long id);
}
