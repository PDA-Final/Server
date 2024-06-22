package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.out.dto.req.FindFollowableUserRequest;
import com.pda.userapplication.services.out.dto.res.FollowInfoResponse;
import com.pda.userapplication.services.out.dto.res.FollowPagingResponse;

import java.util.Optional;

public interface ReadFollowOutputPort {
    boolean isFollow(UserId fromUserId, UserId toUserId);
    FollowPagingResponse findFollowers(FindFollowableUserRequest request);
    FollowPagingResponse findFollowings(FindFollowableUserRequest request);
    FollowInfoResponse getFollowInfo(UserId targetId, Optional<UserId> myId);
}
