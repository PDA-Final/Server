package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.emojis.EmojiRepository;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyAccountRequest;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyEmoRequest;
import com.pda.challengeapplication.mychallenges.dto.response.MyEmoChallengeLog;
import com.pda.challengeapplication.mychallenges.dto.response.MyEmoChallengeLogResponse;
import com.pda.challengeapplication.mychallenges.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyEmoChallengeService {

    private final MyAssetChallengeDetailRepository myAssetChallengeDetailRepository;
    private final MyAssetChallengeRepository myAssetChallengeRepository;
    private final MyChallengeRepository myChallengeRepository;
    private final EmojiRepository emojiRepository;

    public MyAssetChallengeDetail createMyEmoAsset(PostMyAccountRequest postMyAccontRequest) {
        MyAssetChallengeDetail myAssetChallengeDetail = postMyAccontRequest.convertToAccountEntity();
        return myAssetChallengeDetailRepository.save(myAssetChallengeDetail);
    }

    public MyAssetChallengeDetail checkMyEmoAsset(long myChallengeId) {
        return myAssetChallengeDetailRepository.findByMyChallengeId(myChallengeId);
    }

    public void createMyEmoLog(PostMyEmoRequest postMyEmoRequest) {
        MyAssetChallenge myAssetChallenge = postMyEmoRequest.convertToAccountEntity();
        myAssetChallengeRepository.save(myAssetChallenge);
    }

    public MyEmoChallengeLogResponse readAllEmoChallengeLog(long mId) {
        List<MyAssetChallenge> echallenges = myAssetChallengeRepository.findByMyChallengeId(mId);
        int totalprice =0;
        List<MyEmoChallengeLog> mcl = echallenges.stream()
                .map((c)->MyEmoChallengeLog.builder()
                        .savingAt(LocalDate.now())
                        .price(emojiRepository.findById(c.getEmojiId()).get().getPrice())
                        .emotion(emojiRepository.findById(c.getEmojiId()).get().getEmotion())
                        .emojiUrl(emojiRepository.findById(c.getEmojiId()).get().getImgUrl())
                        .build())
                .toList();
        for(MyEmoChallengeLog c: mcl){
            totalprice += c.getPrice();
        }

        MyEmoChallengeLogResponse mr = new MyEmoChallengeLogResponse(mcl,totalprice);
        return mr;
    }

    @Scheduled(cron = "0 55 23 * * *")
    public void checkChallengeStatus() {
        log.info("시작");
        String isSuccess = "진행중";
        // 감정저축 챌린지이면서 진행중인것들
        List<MyChallenge> successChallenge = myChallengeRepository.findAll().stream().filter(c-> c.getStatus().equals("진행중")&& c.getChallenge().getId() ==1) .toList();

        for(MyChallenge s: successChallenge){
            log.info(s.toString());
        }

        for(MyChallenge s: successChallenge){
            log.info("진행중인 챌린지, {}", s.getChallenge().getName());
            if(myAssetChallengeRepository.findByMyChallengeIdAndSavingAt(s.getId(),LocalDate.now()) == null){
                // 그날 감정 저축한 기록이 없으면 해당 myChallengeId는 실패로
                isSuccess = "실패";
            }else if(s.getEndAt() == LocalDate.now().plusDays(1) ){
                isSuccess = "성공";
            }

            s.editMyChallengeStatus(isSuccess);
            myChallengeRepository.save(s);
        }

    }
}


