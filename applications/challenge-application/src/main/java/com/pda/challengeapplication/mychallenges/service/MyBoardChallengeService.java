package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.mychallenges.dto.request.PostMyBoardChallengeRequest;
import com.pda.challengeapplication.mychallenges.repository.MyBoardChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyBoardChallengeRepository;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MyBoardChallengeService {

    private final MyBoardChallengeRepository myBoardChallengeRepository;
    private final MyChallengeRepository myChallengeRepository;

    public MyChallenge participateBoardChallenge(PostMyBoardChallengeRequest postMyBoardChallengeRequest) {
        MyChallenge myChallenge = myChallengeRepository.findById(postMyBoardChallengeRequest.getMyChallengeId()).get();
        MyBoardChallenge myBoardChallenge = postMyBoardChallengeRequest.convertToAccountEntity(myChallenge);
        myBoardChallengeRepository.save(myBoardChallenge);
        myChallenge.editMyChallengeStatus("성공");
        myChallenge.editMyChallengeEndAt(LocalDate.now());
        return myChallengeRepository.save(myChallenge);

    }


}
