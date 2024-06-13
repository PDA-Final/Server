package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyChallengeDetailRepository extends JpaRepository<MyChallengeDetail, Integer> {
}
