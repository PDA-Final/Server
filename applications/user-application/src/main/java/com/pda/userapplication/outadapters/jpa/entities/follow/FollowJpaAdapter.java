package com.pda.userapplication.outadapters.jpa.entities.follow;

import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.outadapters.jpa.converter.UserRoleConverter;
import com.pda.userapplication.outadapters.jpa.entities.user.UserEntity;
import com.pda.userapplication.services.out.ReadFollowOutputPort;
import com.pda.userapplication.services.out.SaveFollowOutputPort;
import com.pda.userapplication.services.out.dto.req.FindFollowableUserRequest;
import com.pda.userapplication.services.out.dto.res.FindFollowableUserResponse;
import com.pda.userapplication.services.out.dto.res.FollowPagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowJpaAdapter implements ReadFollowOutputPort, SaveFollowOutputPort {
    private final FollowRepository followRepository;

    @Override
    public boolean isFollow(UserId fromUserId, UserId toUserId) {
        return followRepository.isFollow(fromUserId.toLong(), toUserId.toLong()) > 0;
    }

    @Override
    public FollowPagingResponse findFollowers(final FindFollowableUserRequest request) {
        List<FollowableUser> follows = followRepository.findFollowersByToUserIdWithMyId(
            request.getFromId(), request.getToId(), request.getLastIndex(), request.getLimit());
        Long lastIndex = follows.isEmpty()?null:follows.get(0).getFollowId();
        boolean isLast = true;
        Long count = followRepository.countByToUser(UserEntity.builder()
            .id(request.getToId())
            .build());

        for(FollowableUser followableUser : follows) {
            if (lastIndex > followableUser.getFollowId())
                lastIndex = followableUser.getFollowId();
        }

        FollowEntity lastFollow = followRepository.findLastFollowByToUserId(request.getToId())
            .orElse(null);

        if (lastFollow != null)
            isLast = lastFollow.getId().equals(lastIndex);

        return FollowPagingResponse.builder()
            .lastIndex(lastIndex)
            .isLast(isLast)
            .totalCount(count)
            .users(from(follows))
            .build();
    }

    @Override
    public FollowPagingResponse findFollowings(FindFollowableUserRequest request) {
        List<FollowableUser> follows = followRepository.findFollowingsByFromUserId(
            request.getToId(), request.getFromId(), request.getLastIndex(), request.getLimit());
        Long lastIndex = follows.isEmpty()?null:follows.get(0).getFollowId();
        boolean isLast = true;
        Long count = followRepository.countByFromUser(UserEntity.builder()
                .id(request.getFromId())
            .build());

        for(FollowableUser followableUser : follows) {
            if (lastIndex > followableUser.getFollowId())
                lastIndex = followableUser.getFollowId();
        }

        FollowEntity lastFollowing = followRepository.findLastFollowByFromUserId(request.getFromId())
            .orElse(null);

        if (lastFollowing != null)
            isLast = lastFollowing.getId().equals(lastIndex);

        return FollowPagingResponse.builder()
            .lastIndex(lastIndex)
            .isLast(isLast)
            .totalCount(count)
            .users(from(follows))
            .build();
    }

    @Override
    public Long follow(UserId fromId, UserId toId) {
        followRepository.save(FollowEntity.builder()
                .fromUser(UserEntity.builder()
                    .id(fromId.toLong())
                    .build())
                .toUser(UserEntity.builder()
                    .id(toId.toLong())
                    .build())
            .build());
        return followRepository.countByToUserId(toId.toLong());
    }

    @Override
    public Long unfollow(UserId fromId, UserId toId) {
        followRepository.deleteByFromUserIdAndToUserId(fromId.toLong(), toId.toLong());
        return followRepository.countByToUserId(toId.toLong());
    }

    private List<FindFollowableUserResponse> from(List<FollowableUser> follows) {
        return follows
            .stream().map((follow) -> FindFollowableUserResponse.builder()
                .followId(follow.getFollowId())
                .userId(follow.getUserId())
                .nickname(follow.getNickname())
                .profileImage(follow.getProfileImage())
                .tofinId(follow.getTofinId())
                .role(new UserRoleConverter().convertToEntityAttribute(follow.getRole()))
                .followStatus(follow.getFollowStatus().equals(1))
                .build())
            .toList();
    }
}
