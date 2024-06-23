package com.pda.challengeapplication.mychallenges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAssetChallengeDetailRepository extends JpaRepository<MyAssetChallengeDetail, Integer> {
    MyAssetChallengeDetail save(MyAssetChallengeDetail mc);
    MyAssetChallengeDetail findByMyChallengeId(long myChallengeId);
}
