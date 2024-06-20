package com.pda.challengeapplication.challenges.service;

import com.pda.challengeapplication.challenges.dto.request.ModifyCorpChallengeRequest;
import com.pda.challengeapplication.challenges.dto.request.PostCorpChallengeRequest;
import com.pda.challengeapplication.challenges.dto.response.ChallengeSummaryResponse;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.ChallengeRepository;
import com.pda.challengeapplication.challenges.repository.CorpChallengeDetail;
import com.pda.challengeapplication.challenges.repository.CorpChallengeDetailRepository;
import com.pda.challengeapplication.mychallenges.repository.MyChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorpChallengeService {

    private final ChallengeRepository challengeRepository;
    private final CorpChallengeDetailRepository corpChallengeDetailRepository;
    private final MyChallengeRepository myChallengeRepository;

    //등록
    @Transactional
    public Challenge createCorpChallenge(PostCorpChallengeRequest corpChallengeDTO) {
        Challenge savedChallenge = challengeRepository.save(corpChallengeDTO.convertToChallengeEntity());
        CorpChallengeDetail corpChallengeDetail = corpChallengeDTO.convertToCCDEntity(savedChallenge);
        corpChallengeDetail.setChallengeId(savedChallenge.getId());
        corpChallengeDetailRepository.save(corpChallengeDetail);
        return savedChallenge;
    }

    //조회 (등록 최신순, 마감기한순)
    public List<ChallengeSummaryResponse> readAllCorpChallengeByNew() {
        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findAll();
        return challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .term(challenge.getChallenge().getTerm())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .participation(myChallengeRepository.selectAllJPQL(challenge.getChallengeId()))
                        .build())
                .filter((challenge) -> !isDone(challenge.getEndAt()))
                .toList();
    }

    public List<ChallengeSummaryResponse> readAllCorpChallengeByEnd() {
        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findAll();
        List<ChallengeSummaryResponse> challengeSummaryResponses = challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .term(challenge.getChallenge().getTerm())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .participation(myChallengeRepository.selectAllJPQL(challenge.getChallengeId()))
                        .build())
                .filter((challenge) -> !isDone(challenge.getEndAt()))
                .sorted(Comparator.comparing(ChallengeSummaryResponse::getEndAt))
                .toList();

        return challengeSummaryResponses;
    }

    // 특정 기업 챌린지 - 진행중
    public List<ChallengeSummaryResponse> findCorpChallenge(long id) {
        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findByCorpId(id);
        List<ChallengeSummaryResponse> challengeSummaryResponses = challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
                        .description(challenge.getChallenge().getDescription())
                        .logoUrl(challenge.getChallenge().getLogoUrl())
                        .endAt(challenge.getChallenge().getEndAt())
                        .challengeUrl(challenge.getChallengeUrl())
                        .corpName(challenge.getCorpName())
                        .participation(myChallengeRepository.selectAllJPQL(challenge.getChallengeId()))
                        .build())
                .filter((challenge) -> isDone(challenge.getEndAt()))
                .sorted(Comparator.comparing(ChallengeSummaryResponse::getEndAt))
                .toList();
        return challengeSummaryResponses;
    }

    // 특정 기업 챌린지 - 마감
    public List<ChallengeSummaryResponse> findEndCorpChallenge(long id) {

        List<CorpChallengeDetail> challengeList = corpChallengeDetailRepository.findByCorpId(id);
        List<ChallengeSummaryResponse> challengeSummaryResponses = challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getChallenge().getId())
                        .challengeType(challenge.getChallenge().getChallengeType())
                        .name(challenge.getChallenge().getName())
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

    // 수정
    public Challenge modifyCorpChallenge(ModifyCorpChallengeRequest modifyCorpChallenge, long id) {
        Challenge challenge = challengeRepository.findById(id);
        CorpChallengeDetail corpChallengeDetail = corpChallengeDetailRepository.findByChallengeId(id);

        challenge.editChallenge(modifyCorpChallenge.getName(),modifyCorpChallenge.getDescription(),modifyCorpChallenge.getLogoUrl(), modifyCorpChallenge.getStartAt(),modifyCorpChallenge.getEndAt());
        challengeRepository.save(challenge);
        corpChallengeDetail.editCorpChallengeDetail(modifyCorpChallenge.getChallengeUrl(), challenge);
        corpChallengeDetailRepository.save(corpChallengeDetail);

        return challengeRepository.findById(id);


    }


    public boolean isDone(LocalDate t) {
        LocalDate currentTime = LocalDate.now();
        if (t.isBefore(currentTime)) {
            return true;
        } else {
            return false;
        }
    }


}
