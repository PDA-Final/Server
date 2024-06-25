package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.UserDetail;
import com.pda.userapplication.services.out.dto.res.AssetInfoResponse;

public interface GetAssetsOutputPort {
    AssetInfoResponse getAssets(UserDetail userDetail);
    AssetInfoResponse getPortfolio(UserDetail userDetail);
}
