package com.pda.challengeapplication.mychallenges.service;

import com.pda.challengeapplication.challenges.repository.Challenge;
import com.pda.challengeapplication.challenges.repository.ChallengeDetailRepository;
import com.pda.challengeapplication.challenges.repository.ChallengeRepository;
import com.pda.challengeapplication.challenges.repository.CorpChallengeDetailRepository;
import com.pda.challengeapplication.mychallenges.dto.request.PostMyChallengeRequest;
import com.pda.challengeapplication.mychallenges.dto.response.MyChallengeBadgeResponse;
import com.pda.challengeapplication.mychallenges.dto.response.MyChallengeResponse;
import com.pda.challengeapplication.mychallenges.repository.MyAssetChallengeRepository;
import com.pda.challengeapplication.mychallenges.repository.MyChallenge;
import com.pda.challengeapplication.mychallenges.repository.MyChallengeRepository;
import com.pda.exceptionhandler.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyChallengeService {
    private final CorpChallengeDetailRepository corpChallengeDetailRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeDetailRepository challengeDetailRepository;
    private final MyChallengeRepository myChallengeRepository;
    private final MyAssetChallengeRepository myAssetChallengeRepository;

    //진행중인 유저 챌린지
    public List<MyChallengeResponse> findChallengeByUserId(Long userId) {
        List<MyChallenge> myChallenge = myChallengeRepository.findByUserId(userId);
        List<MyChallengeResponse> returnList= new ArrayList<>();
        for(MyChallenge mc: myChallenge){
            long id = mc.getId();
            long challengeId = mc.getChallenge().getId();
            int challengeType = mc.getChallenge().getChallengeType();
            String corpName = ""; String challengeUrl = ""; Integer progress = 0;
            String name =  mc.getChallenge().getName();
            String description = mc.getChallenge().getDescription();
            String logoUrl = mc.getChallenge().getLogoUrl();
            LocalDate endAt = mc.getEndAt();
            String status = mc.getStatus();
            Integer term = mc.getChallenge().getTerm();

            if(challengeType ==0){
                corpName = mc.getChallenge().getCorpChallengeDetail().getCorpName();
                challengeUrl = mc.getChallenge().getCorpChallengeDetail().getChallengeUrl();
                progress =  null;
            }else if(challengeType ==1){
                corpName = null; challengeUrl = null;
                int successCnt= myAssetChallengeRepository.findByMyChallengeId(mc.getId())
                        .stream().filter(c->c.isSuccess()).toList().size();
                System.out.println(successCnt);
                System.out.println(mc.getChallenge().getTerm());
                int challenge1term = mc.getChallenge().getTerm();
                progress = (int)Math.round((double)successCnt / challenge1term * 100);
                System.out.println(progress);

            }else if(challengeType ==2){
                corpName = null; challengeUrl = null;
                progress = null;
            }

            MyChallengeResponse mr = MyChallengeResponse.builder()
                    .myChallengeId(id)
                    .challengeId(challengeId)
                    .challengeType(challengeType)
                    .name(name)
                    .description(description)
                    .logoUrl(logoUrl)
                    .challengeUrl(challengeUrl)
                    .corpName(corpName)
                    .endAt(endAt)
                    .status(status)
                    .progress(progress)
                    .participants(myChallengeRepository.selectAllJPQL(id))
                    .term(term)
                    .build();

            returnList.add(mr);
        }

        return returnList.stream()
                .filter(c -> c.getStatus().equals("진행중"))
                .collect(Collectors.toList());

    }

    //마감된 유저 챌린지
    public List<MyChallengeResponse> findClosedChallengeByUserId(Long userId) {
        List<MyChallenge> myChallenge = myChallengeRepository.findByUserId(userId);
        List<MyChallengeResponse> returnList= new ArrayList<>();
        for(MyChallenge mc: myChallenge){
            long id = mc.getId();
            long challengeId = mc.getChallenge().getId();
            int challengeType = mc.getChallenge().getChallengeType();
            String corpName = ""; String challengeUrl = ""; Integer progress = 0;
            String name =  mc.getChallenge().getName();
            String description = mc.getChallenge().getDescription();
            String logoUrl = mc.getChallenge().getLogoUrl();
            LocalDate endAt = mc.getEndAt();
            String status = mc.getStatus();

            if(challengeType ==0){
                corpName = mc.getChallenge().getCorpChallengeDetail().getCorpName();
                challengeUrl = mc.getChallenge().getCorpChallengeDetail().getChallengeUrl();
                progress =  null;
            }else{
                corpName = null; challengeUrl = null;
                progress = null;
            }

            MyChallengeResponse mr = MyChallengeResponse.builder()
                    .myChallengeId(id)
                    .challengeId(challengeId)
                    .challengeType(challengeType)
                    .name(name)
                    .description(description)
                    .logoUrl(logoUrl)
                    .challengeUrl(challengeUrl)
                    .corpName(corpName)
                    .endAt(endAt)
                    .status(status)
                    .progress(progress)
                    .build();

            returnList.add(mr);
        }

        return returnList.stream()
                .filter(c -> !c.getStatus().equals("진행중"))
                .toList();

    }

    // 챌린지 성공, 실패 여부 변경
    public MyChallenge changeChallengeStatus(long mcId,String status) {
        MyChallenge mc = myChallengeRepository.findById(mcId);
        mc.editMyChallengeStatus(status);
        myChallengeRepository.save(mc);

        return myChallengeRepository.findById(mcId);
    }

    // 챌린지 참여 여부 조회
    public boolean checkMyChallenge(long uid, long cid) {
        List<MyChallenge> mc = myChallengeRepository.findByUserIdAndChallengeId(uid, cid);
        mc.stream().filter((c) -> c.getStatus().equals("진행중"));
        if(mc.size()==0)
            return true;
        else
            return false;
    }



    // 게시글 챌린지 참여
    public MyChallenge participateChallenge(PostMyChallengeRequest postMyChallengeRequest, long uid) {

        if(myChallengeRepository.selectAllJPQL2(postMyChallengeRequest.getChallengeId(), uid) != 0){
            throw new ConflictException("이미 참여중인 챌린지입니다");
        }

       Challenge c = challengeRepository.findById(postMyChallengeRequest.getChallengeId());

       LocalDate endAt;
       LocalDate startAt = LocalDate.now();
        if (c.getTerm() == null) {
            endAt = LocalDate.of(9999, 12, 31); // 매우 먼 미래 날짜로 설정
        } else {
            endAt = startAt.plusDays(c.getTerm());
        }

       MyChallenge mc = new MyChallenge(postMyChallengeRequest.getId(),c , uid, startAt, endAt,"진행중");
       return myChallengeRepository.save(mc);
    }

    // 뱃지 리스트
    public List<MyChallengeBadgeResponse> readAllChallengeBadge(Long id) {
        List<MyChallenge> myChallenges = myChallengeRepository.findByUserId(id).stream().filter((c)-> c.getStatus() =="성공").collect(Collectors.toList());
        List<MyChallengeBadgeResponse> bl = new ArrayList<>();
        for(MyChallenge mc: myChallenges){
            String imgUrl = mc.getChallenge().getLogoUrl();
            String bName;
            String cName;

            if(mc.getChallenge().getChallengeType() ==0){
                bName = null;
                cName = corpChallengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).getCorpName();
            }else{
                bName = challengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).get().getBadgeName();
                cName = challengeDetailRepository.findByChallengeId(mc.getChallenge().getId()).get().getChallenge().getName();
            }

            MyChallengeBadgeResponse mbr = MyChallengeBadgeResponse.builder()
                    .imgUrl(imgUrl)
                    .badgeName(bName)
                    .challengeName(cName)
                    .build();
            bl.add(mbr);
        }

        return bl;


    }

    @Scheduled(cron = "0 0 * * * *")
    public void checkChallengeStatus(){

        List<MyChallenge> c = myChallengeRepository.selectAllChallenge();

        for(MyChallenge mc : c){
            if(mc.getEndAt().isBefore(LocalDate.now()) ){
                mc.editMyChallengeStatus("실패");
                // TODO 자산저축챌린지가 아닌 경우 실패 알림
            }
            myChallengeRepository.save(mc);
        }
    }


}
