package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyEmoChallengeRepository extends JpaRepository<MyEmoChallenge, Integer> {
    List<MyEmoChallenge> findByMyChallengeId(Integer myChallengeId);
}
