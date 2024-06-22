package com.pda.userapplication.outadapters.jpa.entities.follow;

import com.pda.userapplication.outadapters.jpa.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
    @Query(value = "select count(*) > 0 from follow " +
        "where from_user_id =:from and to_user_id = :to " +
        "limit 1", nativeQuery = true)
    Long isFollow(@Param("from") Long from, @Param("to") Long toUserId);

    @Query("select count(*) from FollowEntity f " +
        "where f.toUser.id = :toUserId")
    Long countByToUserId(@Param("toUserId") Long toUserId);

    @Modifying
    @Query("delete from FollowEntity f " +
        "where f.fromUser.id = :fromUserId and f.toUser.id = :toUserId")
    void deleteByFromUserIdAndToUserId(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Query(value = "select f.id as followId, u.id as userId, u.nickname as nickname, u.profile_img as profileImage, u.role as role, u.tofin_id as tofinId, " +
        "IFNULL(( " +
        "   select count(*) from follow f2 " +
        "   where f2.from_user_id = :myId " +
        "   and f.from_user_id = f2.to_user_id" +
        "), 0) as followStatus " +
        "from follow f left join user u " +
        "on f.from_user_id = u.id " +
        "where f.to_user_id = :toId and (:lastIndex is null or f.id < :lastIndex) " +
        "order by f.id desc limit :limit", nativeQuery = true)
    List<FollowableUser> findFollowersByToUserIdWithMyId(@Param("myId") Long myId, @Param("toId") Long toId,
                                @Param("lastIndex") Long lastIndex, @Param("limit") Long limit);

    @Query("select f from FollowEntity f where f.toUser.id = :toId order by f.id limit 1")
    Optional<FollowEntity> findLastFollowByToUserId(@Param("toId") Long toUserId);

    @Query(value = "select f.id as followId, u.id as userId, u.nickname as nickname, u.profile_img as profileImage, u.role as role, u.tofin_id as tofinId, " +
        "IFNULL(( " +
        "   select count(*) from follow f2 " +
        "   where f2.from_user_id = :myId " +
        "   and f.to_user_id = f2.to_user_id" +
        "), 0) as followStatus " +
        "from follow f left join user u " +
        "on f.to_user_id = u.id " +
        "where f.from_user_id = :fromId and (:lastIndex is null or f.id < :lastIndex) " +
        "order by f.id desc limit :limit", nativeQuery = true)
    List<FollowableUser> findFollowingsByFromUserId(@Param("myId") Long myId, @Param("fromId") Long fromId,
                                                    @Param("lastIndex") Long lastIndex, @Param("limit") Long limit);

    @Query("select f from FollowEntity f where f.fromUser.id = :fromId order by f.id limit 1")
    Optional<FollowEntity> findLastFollowByFromUserId(@Param("fromId") Long fromId);

    Long countByFromUser(UserEntity userEntity);
    Long countByToUser(UserEntity userEntity);


    @Query(value = "select sum(if(f.from_user_id = :userId, 1, 0)) as followers, " +
        "sum(if(f.to_user_id = :userId, 1, 0)) as followings, " +
        "sum(if(:myId is not null and f.to_user_id = :userId and f.from_user_id = :myId, 1, 0)) as isFollow " +
        "from follow f " +
        "where f.from_user_id = :userId or f.to_user_id = :userId", nativeQuery = true)
    FollowInfo findFollowInfoBy(@Param("userId") Long userId, @Param("myId") Long myId);
}
