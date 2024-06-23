package com.pda.userapplication.outadapters.jpa.entities.follow;

// 팔로워, 팔로잉
public interface FollowableUser {
    Long getFollowId();
    Long getUserId();
    String getNickname();
    String getProfileImage();
    String getTofinId();
    Integer getRole();
    Integer getFollowStatus();
}
