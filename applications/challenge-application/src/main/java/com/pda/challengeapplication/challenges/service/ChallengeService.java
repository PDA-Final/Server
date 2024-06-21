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
import java.util.Optional;

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
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallengeId())
                        .challengeType(challengeRepository.findById(challenge.getChallengeId()).getChallengeType())
                        .name(challengeRepository.findById(challenge.getChallengeId()).getName())
                        .description(challengeRepository.findById(challenge.getChallengeId()).getDescription())
                        .logoUrl(challengeRepository.findById(challenge.getChallengeId()).getLogoUrl())
                        .endAt(challengeRepository.findById(challenge.getChallengeId()).getEndAt())
                        .term(challengeRepository.findById(challenge.getChallengeId()).getTerm())
                        .reward(challenge.getReward())
                        .participation(myChallengeRepository.selectAllJPQL(challenge.getChallengeId()))
                        .build())
                .toList();
    }

    // 상세 조회
    public ChallengeDetailResponse findChallenge(long id, long uid) {
       Optional<ChallengeDetail> challengeDetail = challengeDetailRepository.findByChallengeId(id);
       return ChallengeDetailResponse.builder()
               .id(challengeDetail.get().getChallenge().getId())
               .challengeType(challengeDetail.get().getChallenge().getChallengeType())
               .name(challengeDetail.get().getChallenge().getName())
               .description(challengeDetail.get().getChallenge().getDescription())
               .logoUrl(challengeDetail.get().getChallenge().getLogoUrl())
               .startAt(challengeDetail.get().getChallenge().getStartAt())
               .endAt(challengeDetail.get().getChallenge().getEndAt())
               .term(challengeDetail.get().getChallenge().getTerm())
               .detailDescription(challengeDetail.get().getDetailDescription())
               .badgeName(challengeDetail.get().getBadgeName())
               .standardNum(challengeDetail.get().getStandardNum())
               .standardCg(challengeDetail.get().getStandardCg())
               .reward(challengeDetail.get().getReward())
               .participation(myChallengeRepository.selectAllJPQL(challengeDetail.get().getChallengeId()))
               .status(myChallengeRepository.selectstatus(challengeDetail.get().getChallengeId(), uid))
               .build();
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

        if(challengeDetails.isEmpty()){
            throw new NotFoundException("해당 챌린지를 찾을 수 없습니다");

        }
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



