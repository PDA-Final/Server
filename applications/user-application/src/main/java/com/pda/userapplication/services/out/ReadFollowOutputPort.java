package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.out.dto.req.FindFollowableUserRequest;
import com.pda.userapplication.services.out.dto.res.FollowPagingResponse;

public interface ReadFollowOutputPort {
    boolean isFollow(UserId fromUserId, UserId toUserId);
    FollowPagingResponse findFollowers(FindFollowableUserRequest request);
    FollowPagingResponse findFollowings(FindFollowableUserRequest request);
}
