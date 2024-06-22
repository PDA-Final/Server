package com.pda.userapplication.services.in;


import com.pda.userapplication.services.in.dto.req.SearchUserServiceRequest;
import com.pda.userapplication.services.in.dto.res.GetUserPagingResponse;

public interface GetUserUseCase {
    GetUserPagingResponse searchUserByNickname(SearchUserServiceRequest request);
}
