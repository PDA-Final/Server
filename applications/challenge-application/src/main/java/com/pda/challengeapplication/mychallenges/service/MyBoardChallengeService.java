package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.mychallenges.dto.request.PostMyBoardChallengeRequest;
import com.pda.challengeapplication.mychallenges.repository.MyBoardChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyBoardChallengeRepository;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MyBoardChallengeService {

    private final MyBoardChallengeRepository myBoardChallengeRepository;
    private final MyChallengeRepository myChallengeRepository;

    // 게시글 작성시
    @Async
    public void participateBoardChallenge(PostMyBoardChallengeRequest postMyBoardChallengeRequest) {
        MyChallenge myChallenge = myChallengeRepository.selectMyBoardChallenge(postMyBoardChallengeRequest.getChallengeId(), postMyBoardChallengeRequest.getUsesrId());
        MyBoardChallenge myBoardChallenge = postMyBoardChallengeRequest.convertToAccountEntity(myChallenge);
        myBoardChallengeRepository.save(myBoardChallenge);
        log.info("process success at participate board challenge");
        //myChallenge.editMyChallengeStatus("성공");
        //myChallenge.editMyChallengeEndAt(LocalDate.now());
        //return myChallengeRepository.save(myChallenge);

    }

    //게시글 좋아요 10개 이상 달성
    @Async
    public void successBoardChallenge(long boardId){
        MyBoardChallenge mb = myBoardChallengeRepository.findByBoardId(boardId);
        MyChallenge m = myChallengeRepository.findById(mb.getMyChallengeId());
        m.editMyChallengeStatus("성공");
        m.editMyChallengeEndAt(LocalDate.now());
        myChallengeRepository.save(m);
        // TODO: 알림 보내기
    }


}
