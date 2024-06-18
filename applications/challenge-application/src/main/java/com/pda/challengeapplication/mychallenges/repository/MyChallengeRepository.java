package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Integer> {
    List<MyChallenge> findByUserId(long userId);
    Optional<MyChallenge> findById(long id);

    List<MyChallenge> findByUserIdAndChallengeId(long uid, long cid);
}
