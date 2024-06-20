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

    // 게시글 작성시
    public void participateBoardChallenge(PostMyBoardChallengeRequest postMyBoardChallengeRequest) {
        MyChallenge myChallenge = myChallengeRepository.findById(postMyBoardChallengeRequest.getMyChallengeId());
        MyBoardChallenge myBoardChallenge = postMyBoardChallengeRequest.convertToAccountEntity(myChallenge);
        myBoardChallengeRepository.save(myBoardChallenge);
        //myChallenge.editMyChallengeStatus("성공");
        //myChallenge.editMyChallengeEndAt(LocalDate.now());
        //return myChallengeRepository.save(myChallenge);

    }

    //게시글 좋아요 10개 이상 달성
    public MyChallenge successBoardChallenge(long boardId){
        MyBoardChallenge mb = myBoardChallengeRepository.findByBoardId(boardId);
        MyChallenge m = myChallengeRepository.findById(mb.getMyChallengeId());
        m.editMyChallengeStatus("성공");
        m.editMyChallengeEndAt(LocalDate.now());
        return myChallengeRepository.save(m);
    }


}
