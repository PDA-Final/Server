package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Integer> {
    List<MyChallenge> findByUserId(long userId);
    MyChallenge findById(long id);
    List<MyChallenge> findByUserIdAndChallengeId(long uid, long cid);
    //검색
    @Query(value = "SELECT COUNT(mc) FROM MyChallenge mc WHERE mc.challenge.id = :challengeId AND mc.status = '진행중'")
    int selectAllJPQL(@Param("challengeId") long challengeId);

    @Query(value = "SELECT COUNT(mc) FROM MyChallenge mc WHERE mc.challenge.id = :challengeId AND mc.status = '진행중' AND mc.userId = :userId")
    int selectAllJPQL2(@Param("challengeId") long challengeId, @Param("userId") long userId);

    @Query(value = "SELECT mc.status FROM MyChallenge mc WHERE mc.challenge.id = :challengeId AND mc.status = '진행중' AND mc.userId = :userId")
    String selectstatus(@Param("challengeId") long challengeId, @Param("userId") long userId);

    @Query(value = "SELECT mc FROM MyChallenge mc WHERE mc.challenge.id <> 1 AND mc.status = '진행중'")
    List<MyChallenge> selectAllChallenge();

}
