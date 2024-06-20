package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.vo.UserId;

public interface SaveFollowOutputPort {
    // 팔로우 수 리턴
    Long follow(UserId fromId, UserId toId);
    // 팔로우 수 리턴
    Long unfollow(UserId fromId, UserId toId);
}
