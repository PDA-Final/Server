package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.ChallengeRepository;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyBoardChallengeRequest;
import com.pda.challengeapplication.mychallenges.dto.request.outer.SendChallengeResultRequest;
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
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MyBoardChallengeService {

    private final MyBoardChallengeRepository myBoardChallengeRepository;
    private final MyChallengeRepository myChallengeRepository;
    private final ChallengeRepository challengeRepository;
    private final SendMyChallengeResultPort sendMyChallengeResultPort;

    // 게시글 작성시
    @Async
    public void writeBoardChallenge(PostMyBoardChallengeRequest postMyBoardChallengeRequest) {
        Challenge c = challengeRepository.findById(postMyBoardChallengeRequest.getChallengeId());
        //챌린지 참여
        MyChallenge myChallenge = postMyBoardChallengeRequest.convertToMyChallengeEntitiy(c);
        MyBoardChallenge myBoardChallenge = postMyBoardChallengeRequest.convertToBoardEntity(myChallengeRepository.save(myChallenge));
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

        SendResult(m.getChallenge().getName(), m.getChallenge().getLogoUrl(), m.getUserId(), m.getChallenge().getChallengeDetail().getReward());

        // TODO: 알림 보내기
    }

    @Async
    public void SendResult(String challengeName, String logoUrl, Long userId, Integer reward){
        sendMyChallengeResultPort.sendChallengeResult(SendChallengeResultRequest.builder()
                .result(challengeName + "에 성공하여 " + reward + "크레딧을 획득하셨습니다.")
                .userId(userId)
                .transactionDateTime(LocalDateTime.now())
                .logoUrl(logoUrl)
                .build());


    }


}
