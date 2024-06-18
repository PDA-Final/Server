package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBoardChallengeRepository extends JpaRepository<MyBoardChallenge, Integer> {
}
