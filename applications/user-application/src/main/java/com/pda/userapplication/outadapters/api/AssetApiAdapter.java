package com.pda.userapplication.outadapters.api;

import com.pda.apiutils.GlobalResponse;
import com.pda.exceptionhandler.exceptions.InternalServerException;
import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.services.out.GetAssetsOutputPort;
import com.pda.userapplication.services.out.dto.res.AssetInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AssetApiAdapter implements GetAssetsOutputPort {
    private final WebClient webClient;
    @Value("${out-service.asset.url}")
    private String assetUrl;

    @Override
    public AssetInfoResponse getAssets(NormalUser normalUser) {
        Mono<GlobalResponse<AssetInfoResponse>> mono = webClient.get().uri(assetUrl+"/assets")
            .header("front-social-id", normalUser.getFrontSocialId())
            .header("back-social-id", normalUser.getBackSocialId())
            .header("user-social-contact", normalUser.getContact())
            .exchangeToMono(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    throw new InternalServerException("외부 API 연결 실패: " + response.statusCode());
                }

                return response.bodyToMono(new ParameterizedTypeReference<GlobalResponse<AssetInfoResponse>>() {});
            });

        return mono.block().getData();
    }
}
