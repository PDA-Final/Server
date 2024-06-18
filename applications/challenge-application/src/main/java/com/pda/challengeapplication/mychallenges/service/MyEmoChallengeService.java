package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.emojis.EmojiRepository;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyAccountRequest;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyEmoRequest;
import com.pda.challengeapplication.mychallenges.dto.response.MyEmoChallengeLog;
import com.pda.challengeapplication.mychallenges.dto.response.MyEmoChallengeLogResponse;
import com.pda.challengeapplication.mychallenges.repository.MyAssetChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyAssetChallengeDetail;
import com.pda.challengeapplication.mychallenges.repository.MyAssetChallengeDetailRepository;
import com.pda.challengeapplication.mychallenges.repository.MyAssetChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyEmoChallengeService {

    private final MyAssetChallengeDetailRepository myAssetChallengeDetailRepository;
    private final MyAssetChallengeRepository myAssetChallengeRepository;
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
}


