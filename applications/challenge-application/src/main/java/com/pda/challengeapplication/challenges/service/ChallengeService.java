package com.pda.challengeapplication.challenges.service;

import com.pda.challengeapplication.challenges.dto.request.ModifyChallengeRequest;
import com.pda.challengeapplication.challenges.dto.request.PostChallengeRequest;
import com.pda.challengeapplication.challenges.dto.response.ChallengeDetailResponse;
import com.pda.challengeapplication.challenges.dto.response.ChallengeSummaryResponse;
import com.pda.challengeapplication.challenges.repository.*;
import com.pda.challengeapplication.mychallenges.repository.MyChallengeRepository;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeDetailRepository challengeDetailRepository;
    private final CorpChallengeDetailRepository corpChallengeDetailRepository;
    private final MyChallengeRepository myChallengeRepository;

    // 챌린지 등록
    @Transactional
    public Challenge createChallenge(PostChallengeRequest challengeDTO) {
        Challenge savedChallenge = challengeRepository.save(challengeDTO.convertToChallengeEntity());
        ChallengeDetail challengeDetail = challengeDTO.convertToCDEntity(savedChallenge.getId(), savedChallenge);
        challengeDetailRepository.save(challengeDetail);
        return challengeRepository.findFirstByOrderByIdDesc();
    }

    // 조회
    public List<ChallengeSummaryResponse> readAllChallenge() {
        List<ChallengeDetail> challengeList = challengeDetailRepository.findAll();

        System.out.println(challengeList.get(0));
        return challengeList.stream()
                .map((challengeDetail) -> {
                    
                    return ChallengeSummaryResponse.builder()
                        .id(challengeDetail.getChallengeId())
                        .challengeType(challengeDetail.getChallenge().getChallengeType())
                        .name(challengeDetail.getChallenge().getName())
                        .description(challengeDetail.getChallenge().getDescription())
                        .logoUrl(challengeDetail.getChallenge().getLogoUrl())
                        .endAt(challengeDetail.getChallenge().getEndAt())
                        .term(challengeDetail.getChallenge().getTerm())
                        .reward(challengeDetail.getReward())
                        .participation(myChallengeRepository.selectAllJPQL(challengeDetail.getChallengeId()))
                        .build();
                })
                .toList();
    }

    // 상세 조회
    public ChallengeDetailResponse findChallenge(long id, long uid) {

       ChallengeDetail challengeDetail = challengeDetailRepository.findByChallengeId(id)
               .orElseThrow(() -> new NotFoundException("Challenge not found"));
       return ChallengeDetailResponse.builder()
               .id(challengeDetail.getChallenge().getId())
               .challengeType(challengeDetail.getChallenge().getChallengeType())
               .name(challengeDetail.getChallenge().getName())
               .description(challengeDetail.getChallenge().getDescription())
               .logoUrl(challengeDetail.getChallenge().getLogoUrl())
               .startAt(challengeDetail.getChallenge().getStartAt())
               .endAt(challengeDetail.getChallenge().getEndAt())
               .term(challengeDetail.getChallenge().getTerm())
               .detailDescription(challengeDetail.getDetailDescription())
               .badgeName(challengeDetail.getBadgeName())
               .standardNum(challengeDetail.getStandardNum())
               .standardCg(challengeDetail.getStandardCg())
               .reward(challengeDetail.getReward())
               .participation(myChallengeRepository.selectAllJPQL(challengeDetail.getChallengeId()))
               .status(getStatus(challengeDetail.getChallengeId(),uid))
               .build();
    }

    public String getStatus(long cid, long uid){
        if(uid==0){
            return null;
        }else{
            return myChallengeRepository.selectstatus(cid, uid);
        }
    }



    public Challenge modifyChallenge(ModifyChallengeRequest modifyChallenge, long id) {
        Challenge challenge = challengeRepository.findById(id);
        ChallengeDetail challengeDetail = challengeDetailRepository.findByChallengeId(id).get();

        if(challenge == null){
            throw new NotFoundException("존재하지 않는 챌린지입니다");

        }

        challenge.editChallenge(modifyChallenge.getName(),modifyChallenge.getDescription(),modifyChallenge.getLogoUrl(), modifyChallenge.getStartAt(),modifyChallenge.getEndAt());
        challengeRepository.save(challenge);
        challengeDetail.editChallengeDetail(challengeDetail.getDetailDescription(), challenge);
        challengeDetailRepository.save(challengeDetail);

        return challengeRepository.findById(id);

    }

    // 챌린지 검색
    public List<ChallengeSummaryResponse> searchChallengeByName(String searchname) {
        List<Challenge> challengeDetails= challengeRepository.findByNameLike("%"+searchname+"%");
        List<ChallengeSummaryResponse> returnList = new ArrayList<>();

        for(Challenge c : challengeDetails){
            long id = c.getId();
            int challengeType = c.getChallengeType();
            String name = c.getName();
            String description = c.getDescription();
            String logoUrl = c.getLogoUrl();
            LocalDate endAt = c.getEndAt();
            Integer term = c.getTerm();
            String challengeUrl; String corpName; Integer reward;

            if(challengeType ==0){
                challengeUrl = corpChallengeDetailRepository.findByChallengeId(id).getChallengeUrl();
                corpName = corpChallengeDetailRepository.findByChallengeId(id).getCorpName();
                reward = null;
                
            }else{
                challengeUrl = null;
                corpName = null;
                reward = c.getChallengeDetail().getReward();
            }

            ChallengeSummaryResponse cr = ChallengeSummaryResponse.builder()
                    .id(id)
                    .challengeType(challengeType)
                    .name(name)
                    .description(description)
                    .logoUrl(logoUrl)
                    .endAt(endAt)
                    .term(term)
                    .challengeUrl(challengeUrl)
                    .corpName(corpName)
                    .reward(reward)
                    .participation(myChallengeRepository.selectAllJPQL(id))
                    .build();

            returnList.add(cr);
        }



        return returnList;
    }
}



