package com.pda.challengeapplication.challenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeDetailRepository extends JpaRepository<ChallengeDetail, Integer> {
    Optional<ChallengeDetail> findByChallengeId(long ChallengeId);

    ChallengeDetail save(ChallengeDetail challengeDetail);

    List<ChallengeDetail> findAll();

}