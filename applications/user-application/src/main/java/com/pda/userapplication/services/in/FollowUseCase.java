package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.FollowOrUnFollowServiceRequest;
import com.pda.userapplication.services.in.dto.req.GetFollowableServiceRequest;
import com.pda.userapplication.services.in.dto.res.FollowOrUnfollowServiceResponse;
import com.pda.userapplication.services.in.dto.res.GetFollowablePagingResponse;

public interface FollowUseCase {
    FollowOrUnfollowServiceResponse followOrUnfollow(FollowOrUnFollowServiceRequest request);
    GetFollowablePagingResponse getFollowers(GetFollowableServiceRequest request);
    GetFollowablePagingResponse getFollowings(GetFollowableServiceRequest request);
}
