package com.pda.userapplication.outadapters.jpa.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {
    boolean existsByContact(String contact);

    @Query("select ud from UserDetailEntity ud " +
        "left join UserEntity u on ud.user.id = u.id " +
        "where ud.user.id = :userId")
    Optional<UserDetailEntity> findByUserId(@Param("userId") Long userId);
}
