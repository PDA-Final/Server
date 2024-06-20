package com.pda.userapplication.services.in.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowOrUnfollowServiceResponse {
    private long count;
    @JsonProperty("isFollow")
    private boolean follow;

    public static FollowOrUnfollowServiceResponse of(long count, boolean follow) {
        return new FollowOrUnfollowServiceResponse(count, follow);
    }
}
