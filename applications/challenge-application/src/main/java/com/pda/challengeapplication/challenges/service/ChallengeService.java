package com.pda.challengeapplication.challenges.service;

import com.pda.challengeapplication.challenges.ChallengeDTO;
import com.pda.challengeapplication.challenges.ChallengeSummaryResponse;
import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Transactional
    public Challenge createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = challengeDTO.convertToEntity();
        return challengeRepository.save(challenge);
    }

    public List<ChallengeSummaryResponse> readAllChallengeByNew(String sortId) {
        List<Challenge> challengeList = challengeRepository.findAll();
        return challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getId())
                        .challengeType(challenge.getChallengeType())
                        .name(challenge.getName())
                        .participants(challenge.getParticipants())
                        .description(challenge.getDescription())
                        .logoUrl(challenge.getLogoUrl())
                        .endAt(challenge.getEndAt())
                        .build())
                .toList();
    }

    public List<ChallengeSummaryResponse> readAllChallengeByEnd(String sortId) {
        List<Challenge> challengeList = challengeRepository.findAllByOrderByEndAtAsc();
        return challengeList.stream()
                .map((challenge) -> ChallengeSummaryResponse.builder()
                        .id(challenge.getId())
                        .challengeType(challenge.getChallengeType())
                        .name(challenge.getName())
                        .participants(challenge.getParticipants())
                        .description(challenge.getDescription())
                        .logoUrl(challenge.getLogoUrl())
                        .endAt(challenge.getEndAt())
                        .build())
                .toList();
    }


}



