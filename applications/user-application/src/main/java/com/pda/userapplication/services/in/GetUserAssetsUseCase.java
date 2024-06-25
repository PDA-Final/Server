package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.res.AssetInfoServiceResponse;

import java.util.List;

public interface GetUserAssetsUseCase {
    List<AssetInfoServiceResponse> getAccounts(Long userId);
}
