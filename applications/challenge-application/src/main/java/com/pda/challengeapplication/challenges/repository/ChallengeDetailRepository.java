package com.pda.challengeapplication.challenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeDetailRepository extends JpaRepository<ChallengeDetail, Integer> {
    Optional<ChallengeDetail> findByChallengeId(long ChallengeId);
    ChallengeDetail save(ChallengeDetail challengeDetail);

<<<<<<< HEAD
    List<ChallengeDetail> findAll();
=======
    List<ChallengeDetail> findALl();
>>>>>>> 852353e6c8ac1ded403c8435f01e3bc54cb986ab
}
