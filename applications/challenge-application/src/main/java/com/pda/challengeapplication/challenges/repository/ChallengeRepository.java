package com.pda.challengeapplication.challenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
    @Override
    Challenge save(Challenge challenge);

    List<Challenge> findAll();
    List<Challenge> findAllByOrderByEndAtAsc();
    Challenge findFirstByOrderByIdDesc();
}
