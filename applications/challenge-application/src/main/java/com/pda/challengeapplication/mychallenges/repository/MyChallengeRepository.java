package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Integer> {
    List<MyChallenge> findByUserId(Long userId);
}
