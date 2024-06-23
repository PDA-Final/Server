package com.pda.userapplication.outadapters.jpa.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity userEntity);
    boolean existsByTofinId(String tofinId);
    Optional<UserEntity> findByTofinId(String tofinId);
    Optional<UserEntity> findById(Long id);

    @Query("select u from UserEntity u " +
        "where u.nickname like %:nickname% and (:lastIndex is null or u.id < :lastIndex) " +
        "order by u.id desc limit :limit")
    List<UserEntity> searchByNickname(@Param("nickname") String nickname,
                                      @Param("lastIndex") Long lastIndex,
                                      @Param("limit") Long limit);


    long countByNicknameLike(String nickname);

    @Query("select u from UserEntity  u " +
        "where u.nickname like %:nickname% " +
        "order by u.id limit 1 ")
    Optional<UserEntity> findLastByNickname(@Param("nickname") String nickname);
}
