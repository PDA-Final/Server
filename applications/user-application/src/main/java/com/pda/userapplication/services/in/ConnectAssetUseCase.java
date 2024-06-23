package com.pda.userapplication.services.in;

import com.pda.userapplication.services.in.dto.req.ConnectAssetsServiceRequest;
import com.pda.userapplication.services.in.dto.res.ConnectAssetInfoResponse;

import java.util.List;

public interface ConnectAssetUseCase {
    List<ConnectAssetInfoResponse> connectAssets(ConnectAssetsServiceRequest request);
}
