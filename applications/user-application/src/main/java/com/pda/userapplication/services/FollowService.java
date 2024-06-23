package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.FollowUseCase;
import com.pda.userapplication.services.in.dto.req.FollowOrUnFollowServiceRequest;
import com.pda.userapplication.services.in.dto.req.GetFollowableServiceRequest;
import com.pda.userapplication.services.in.dto.res.FollowOrUnfollowServiceResponse;
import com.pda.userapplication.services.in.dto.res.FollowStatusServiceResponse;
import com.pda.userapplication.services.in.dto.res.GetFollowablePagingResponse;
import com.pda.userapplication.services.in.dto.res.GetFollowableResponse;
import com.pda.userapplication.services.out.ReadFollowOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.SaveFollowOutputPort;
import com.pda.userapplication.services.out.dto.req.FindFollowableUserRequest;
import com.pda.userapplication.services.out.dto.res.FollowInfoResponse;
import com.pda.userapplication.services.out.dto.res.FollowPagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService implements FollowUseCase {
    private final ReadUserOutputPort readUserOutputPort;
    private final ReadFollowOutputPort readFollowOutputPort;
    private final SaveFollowOutputPort saveFollowOutputPort;

    @Transactional
    @Override
    public FollowOrUnfollowServiceResponse followOrUnfollow(FollowOrUnFollowServiceRequest request) {
        if (request.getFromId().equals(request.getToId()))
            throw new BadRequestException("자기 자신을 팔로우할 수 없습니다.");

        User fromUser = readUserOutputPort.getUserByUserId(UserId.of(request.getFromId()));
        User toUser = readUserOutputPort.getUserByUserId(UserId.of(request.getToId()));

        // follower 수랑 같이 주기, 팔로우 상태인지 아닌지 체크
        if(readFollowOutputPort.isFollow(fromUser.getId(), toUser.getId())) {
            // follow면 follow 취소
            return FollowOrUnfollowServiceResponse.of(saveFollowOutputPort.unfollow(fromUser.getId(), toUser.getId())
                , false);
        }


        // follow 하기
        Long followCount = saveFollowOutputPort.follow(fromUser.getId(), toUser.getId());
        // kafka 보내기
        return FollowOrUnfollowServiceResponse.of(followCount, true);
    }

    @Override
    public GetFollowablePagingResponse getFollowers(final GetFollowableServiceRequest request) {
        User toUser = readUserOutputPort.getUserByUserId(UserId.of(request.getToId()));

        if (request.getFromId() == null) {
            return from(readFollowOutputPort.findFollowers(FindFollowableUserRequest.builder()
                .toId(toUser.getId().toLong())
                .limit(request.getLimit())
                .lastIndex(request.getLastIndex())
                .build()));
        }

        User fromUser = readUserOutputPort.getUserByUserId(UserId.of(request.getFromId()));
        return from(readFollowOutputPort.findFollowers(FindFollowableUserRequest.builder()
            .toId(toUser.getId().toLong())
            .fromId(fromUser.getId().toLong())
            .limit(request.getLimit())
            .lastIndex(request.getLastIndex())
            .build()));
    }

    @Override
    public GetFollowablePagingResponse getFollowings(GetFollowableServiceRequest request) {
        User fromUser = readUserOutputPort.getUserByUserId(UserId.of(request.getFromId()));

        if (request.getToId() == null) {
            return from(readFollowOutputPort.findFollowings(FindFollowableUserRequest.builder()
                .fromId(fromUser.getId().toLong())
                .limit(request.getLimit())
                .lastIndex(request.getLastIndex())
                .build()));
        }

        User toUser = readUserOutputPort.getUserByUserId(UserId.of(request.getToId()));
        return from(readFollowOutputPort.findFollowings(FindFollowableUserRequest.builder()
            .fromId(fromUser.getId().toLong())
            .toId(toUser.getId().toLong())
            .limit(request.getLimit())
            .lastIndex(request.getLastIndex())
            .build()));
    }

    @Override
    public FollowStatusServiceResponse getFollow(Long id, Long myId) {
        User user = readUserOutputPort.getUserByUserId(UserId.of(id));
        FollowInfoResponse followInfo = readFollowOutputPort
            .getFollowInfo(user.getId(), Optional.ofNullable(myId==null?null:UserId.of(myId)));

        return FollowStatusServiceResponse.builder()
            .follow(followInfo.isFollow())
            .followers(followInfo.getNumOfFollowers())
            .followings(followInfo.getNumOfFollowings())
            .build();
    }

    private GetFollowablePagingResponse from(final FollowPagingResponse response) {
        return GetFollowablePagingResponse.builder()
            .lastIndex(response.getLastIndex())
            .isLast(response.isLast())
            .totalCount(response.getTotalCount())
            .users(response.getUsers().stream().map((follow) -> GetFollowableResponse.builder()
                    .role(follow.getRole())
                    .followId(follow.getFollowId())
                    .userId(follow.getUserId())
                    .followStatus(follow.isFollowStatus())
                    .profileImage(follow.getProfileImage())
                    .tofinId(follow.getTofinId())
                    .nickname(follow.getNickname())
                .build())
                .toList())
            .build();
    }
}
