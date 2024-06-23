package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.res.UserDetailInfoResponse;

public interface GetUserDetailInfo {
    UserDetailInfoResponse getUserDetailInfo(Long userId);
}
