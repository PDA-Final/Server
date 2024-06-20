package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MyAssetChallengeRepository extends JpaRepository<MyAssetChallenge, Integer> {
    List<MyAssetChallenge> findByMyChallengeId(long myChallengeId);
    MyAssetChallenge save(MyAssetChallenge mc);

    MyAssetChallenge findByMyChallengeIdAndSavingAt(long id, LocalDate now);
}
