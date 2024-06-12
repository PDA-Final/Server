package com.pda.challengeapplication.challenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CorpChallengeDetailRepository extends JpaRepository<CorpChallengeDetail, Integer> {
    CorpChallengeDetail save(CorpChallengeDetail corpChallengeDetail);
    List<CorpChallengeDetail> findAll();
    List<CorpChallengeDetail> findByCorpId(Integer id);
    CorpChallengeDetail findByChallengeId(Integer id);
}
