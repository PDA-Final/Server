package com.pda.userapplication.services.out;

import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.services.out.dto.res.AssetInfoResponse;

public interface GetAssetsOutputPort {
    AssetInfoResponse getAssets(NormalUser normalUser);
    AssetInfoResponse getPortfolio(NormalUser normalUser);
}
