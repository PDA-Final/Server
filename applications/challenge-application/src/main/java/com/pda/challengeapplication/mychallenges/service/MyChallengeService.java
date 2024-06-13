package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.challenges.repository.CorpChallengeDetailRepository;
import com.pda.challengeapplication.mychallenges.DTO.Response.MyChallengeResponse;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallengeRepository;
import com.pda.challengeapplication.mychallenges.repository.MyEmoChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyChallengeService {

    private final CorpChallengeDetailRepository corpChallengeDetailRepository;
    private final MyChallengeRepository myChallengeRepository;
    private final MyEmoChallengeRepository myEmoChallengeRepository;
    //진행중인 유저 챌린지
    public List<MyChallengeResponse> findChallengeByUserId(Long userId) {
        List<MyChallenge> myChallenge = myChallengeRepository.findByUserId(userId);
        List<MyChallengeResponse> returnList= new ArrayList<>();
        for(MyChallenge mc: myChallenge){
            Integer id = mc.getId();
            int challengeType = mc.getChallenge().getChallengeType();
            String cropName; String challengeUrl; Integer progress;
            String name =  mc.getChallenge().getName();
            int participants = mc.getChallenge().getParticipants();
            String description = mc.getChallenge().getDescription();
            String logoUrl = mc.getChallenge().getLogoUrl();
            Timestamp endAt = mc.getEndAt();
            String status = mc.getStatus();

            if(challengeType ==0){
                cropName = corpChallengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).getCorpName();
                challengeUrl = corpChallengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).getChallengeUrl();
                progress =  null;
            }else{
                cropName = null; challengeUrl = null;
                progress = myEmoChallengeRepository.findByMyChallengeId(mc.getId()).size();
            }

            MyChallengeResponse mr = MyChallengeResponse.builder()
                    .id(id)
                    .challengeType(challengeType)
                    .name(name)
                    .participants(participants)
                    .description(description)
                    .logoUrl(logoUrl)
                    .challengeUrl(challengeUrl)
                    .corpName(cropName)
                    .endAt(endAt)
                    .status(status)
                    .progress(progress)
                    .build();

            returnList.add(mr);
        }

        return returnList.stream()
                .filter(c -> !isDone(c.getEndAt()))
                .collect(Collectors.toList());

    }

    //마감된 유저 챌린지
    public List<MyChallengeResponse> findClosedChallengeByUserId(Long userId) {
        List<MyChallenge> myChallenge = myChallengeRepository.findByUserId(userId);
        List<MyChallengeResponse> returnList= new ArrayList<>();
        for(MyChallenge mc: myChallenge){
            Integer id = mc.getId();
            int challengeType = mc.getChallenge().getChallengeType();
            String cropName; String challengeUrl; Integer progress;
            String name =  mc.getChallenge().getName();
            int participants = mc.getChallenge().getParticipants();
            String description = mc.getChallenge().getDescription();
            String logoUrl = mc.getChallenge().getLogoUrl();
            Timestamp endAt = mc.getEndAt();
            String status = mc.getStatus();

            if(challengeType ==0){
                cropName = corpChallengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).getCorpName();
                challengeUrl = corpChallengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).getChallengeUrl();
                progress =  null;
            }else{
                cropName = null; challengeUrl = null;
                progress = myEmoChallengeRepository.findByMyChallengeId(mc.getId()).size();
            }

            MyChallengeResponse mr = MyChallengeResponse.builder()
                    .id(id)
                    .challengeType(challengeType)
                    .name(name)
                    .participants(participants)
                    .description(description)
                    .logoUrl(logoUrl)
                    .challengeUrl(challengeUrl)
                    .corpName(cropName)
                    .endAt(endAt)
                    .status(status)
                    .progress(progress)
                    .build();

            returnList.add(mr);
        }

        return returnList.stream()
                .filter(c -> isDone(c.getEndAt()))
                .collect(Collectors.toList());

    }

    public boolean isDone(Timestamp t){
        Timestamp currentTime = new Timestamp(new Date().getTime());
        if(t.after(currentTime)){
            return false;
        } else {
            return true;
        }
    }
}
