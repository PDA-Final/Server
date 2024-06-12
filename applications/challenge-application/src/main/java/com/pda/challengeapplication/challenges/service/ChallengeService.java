package com.pda.challengeapplication.challenges.service;

import com.pda.challengeapplication.challenges.DTO.Request.ModifyChallengeRequest;
import com.pda.challengeapplication.challenges.DTO.Request.ModifyCorpChallengeRequest;
import com.pda.challengeapplication.challenges.DTO.Request.PostChallengeRequest;
import com.pda.challengeapplication.challenges.DTO.Request.PostCorpChallengeRequest;
import com.pda.challengeapplication.challenges.DTO.Response.ChallengeDetailResponse;
import com.pda.challengeapplication.challenges.DTO.Response.ChallengeSummaryResponse;
import com.pda.challengeapplication.challenges.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ChallengeDetailRepository challengeDetailRepository;
    private final CorpChallengeDetailRepository corpChallengeDetailRepository;

    @Transactional
    public Challenge createChallenge(PostChallengeRequest challengeDTO) {
        Challenge savedChallenge = challengeRepository.save(challengeDTO.convertToChallengeEntity());
        ChallengeDetail challengeDetail = challengeDTO.convertToCDEntity(savedChallenge);
        challengeDetail.setChallengeId(savedChallenge.getId());
        challengeDetailRepository.save(challengeDetail);
        return challengeRepository.findFirstByOrderByIdDesc();
    }


    @Transactional
    public Challenge createCorpChallenge(PostCorpChallengeRequest corpChallengeDTO) {
        Challenge savedChallenge = challengeRepository.save(corpChallengeDTO.convertToChallengeEntity());
        CorpChallengeDetail corpChallengeDetail = corpChallengeDTO.convertToCCDEntity(savedChallenge);
        corpChallengeDetail.setChallengeId(savedChallenge.getId());
        corpChallengeDetailRepository.save(corpChallengeDetail);
        return savedChallenge;
    }


    public List<ChallengeSummaryResponse> readAllCorpChallengeByNew() {
        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findAll();
        return challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .participants(challenge.getChallenge().getParticipants())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .build())
                .filter((challenge) -> isDone(challenge.getEndAt()))
                .toList();
    }

    public List<ChallengeSummaryResponse> readAllCorpChallengeByEnd() {
        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findAll();
        List<ChallengeSummaryResponse> challengeSummaryResponses = challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .participants(challenge.getChallenge().getParticipants())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .build())
                .filter((challenge) -> isDone(challenge.getEndAt()))
                .sorted(Comparator.comparing(ChallengeSummaryResponse::getEndAt))
                .toList();

        return challengeSummaryResponses;
    }



    public ChallengeDetailResponse findChallenge(Integer id) {
       Optional<ChallengeDetail> challengeDetail = challengeDetailRepository.findByChallengeId(id);
       return ChallengeDetailResponse.builder()
               .id(challengeDetail.get().getChallenge().getId())
               .challengeType(challengeDetail.get().getChallenge().getChallengeType())
               .name(challengeDetail.get().getChallenge().getName())
               .participants(challengeDetail.get().getChallenge().getParticipants())
               .description(challengeDetail.get().getChallenge().getDescription())
               .logoUrl(challengeDetail.get().getChallenge().getLogoUrl())
               .startAt(challengeDetail.get().getChallenge().getStartAt())
               .endAt(challengeDetail.get().getChallenge().getEndAt())
               .detailDescription(challengeDetail.get().getDetailDescription())
               .build();
    }



    public boolean isDone(Timestamp t){
        Timestamp currentTime = new Timestamp(new Date().getTime());
        if(t.after(currentTime)){
            return true;
        } else {
            return false;
        }
    }


    // 진행중인 특정 기업 챌린지
    public List<ChallengeSummaryResponse> findCorpChallenge(Integer id) {
        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findByCorpId(id);
        List<ChallengeSummaryResponse> challengeSummaryResponses = challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .participants(challenge.getChallenge().getParticipants())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .build())
                .filter((challenge) -> isDone(challenge.getEndAt()))
                .sorted(Comparator.comparing(ChallengeSummaryResponse::getEndAt))
                .toList();
        return challengeSummaryResponses;
    }

    //마감된 특정 기업 챌린지
    public List<ChallengeSummaryResponse> findEndCorpChallenge(Integer id) {

        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findByCorpId(id);
        List<ChallengeSummaryResponse> challengeSummaryResponses = challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .participants(challenge.getChallenge().getParticipants())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .build())
                .filter((challenge) -> !isDone(challenge.getEndAt()))
                .sorted(Comparator.comparing(ChallengeSummaryResponse::getEndAt))
                .toList();
        return challengeSummaryResponses;
    }

    public Challenge modifyChallenge(ModifyChallengeRequest modifyChallenge, Integer id) {
        Challenge challenge = challengeRepository.findById(id).get();
        ChallengeDetail challengeDetail = challengeDetailRepository.findByChallengeId(id).get();
//        if(challenge == null){
//            //예외처리
//        }
        challenge.editChallenge(modifyChallenge.getName(),modifyChallenge.getDescription(),modifyChallenge.getLogoUrl(), modifyChallenge.getStartAt(),modifyChallenge.getEndAt());
        challengeRepository.save(challenge);
        challengeDetail.editChallengeDetail(challengeDetail.getDetailDescription(), challenge);
        challengeDetailRepository.save(challengeDetail);

        return challenge;

    }

    public Challenge modifyCorpChallenge(ModifyCorpChallengeRequest modifyCorpChallenge, Integer id) {
        Challenge challenge = challengeRepository.findById(id).get();
        CorpChallengeDetail corpChallengeDetail = corpChallengeDetailRepository.findByChallengeId(id);

        challenge.editChallenge(modifyCorpChallenge.getName(),modifyCorpChallenge.getDescription(),modifyCorpChallenge.getLogoUrl(), modifyCorpChallenge.getStartAt(),modifyCorpChallenge.getEndAt());
        challengeRepository.save(challenge);
        corpChallengeDetail.editCorpChallengeDetail(modifyCorpChallenge.getChallengeUrl(), challenge);
        corpChallengeDetailRepository.save(corpChallengeDetail);

        return challenge;


    }
}



