package com.pda.userapplication.outadapters.api;

import com.pda.apiutils.GlobalResponse;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.InternalServerException;
import com.pda.userapplication.domains.UserDetail;
import com.pda.userapplication.services.out.CreditOutputPort;
import com.pda.userapplication.services.out.GetAssetsOutputPort;
import com.pda.userapplication.services.out.SaveAssetsOutputPort;
import com.pda.userapplication.services.out.dto.req.TransferCashRequest;
import com.pda.userapplication.services.out.dto.req.TransferCreditRequest;
import com.pda.userapplication.services.out.dto.res.AssetInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiAdapter implements GetAssetsOutputPort, CreditOutputPort, SaveAssetsOutputPort {
    private final WebClient webClient;
    @Value("${out-service.asset.url}")
    private String assetUrl;
    @Value("${out-service.product.url}")
    private String productUrl;
    @Value("${out-service.credit.url}")
    private String creditUrl;

    @Override
    public AssetInfoResponse getAssets(UserDetail userDetail) {
        Mono<GlobalResponse<AssetInfoResponse>> mono = webClient.get().uri(assetUrl+"/assets")
            .header("front-social-id", userDetail.getFrontSocialId())
            .header("back-social-id", userDetail.getBackSocialId())
            .header("user-social-contact", userDetail.getContact())
            .exchangeToMono(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    throw new InternalServerException("외부 API 연결 실패: " + response.statusCode());
                }

                return response.bodyToMono(new ParameterizedTypeReference<GlobalResponse<AssetInfoResponse>>() {});
            });

        return mono.block().getData();
    }

    @Override
    public AssetInfoResponse getPortfolio(UserDetail userDetail) {
        Mono<GlobalResponse<AssetInfoResponse>> mono = webClient.get().uri(assetUrl+"/assets?targets=PORTFOLIO&targets=ACCOUNT")
            .header("front-social-id", userDetail.getFrontSocialId())
            .header("back-social-id", userDetail.getBackSocialId())
            .header("user-social-contact", userDetail.getContact())
            .exchangeToMono(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    throw new InternalServerException("외부 API 연결 실패: " + response.statusCode());
                }

                return response.bodyToMono(new ParameterizedTypeReference<GlobalResponse<AssetInfoResponse>>() {});
            });

        return mono.block().getData();
    }

    @Override
    public void consumeCredit(Long amount, String token) {
        Map<String, Object> body = new HashMap<>();
        body.put("amount", amount);
        body.put("transactionDateTime", LocalDateTime.now());

        webClient.post().uri(creditUrl+"/credit/withdraw")
            .header("Authorization", String.format("Bearer %s", token))
            .body(BodyInserters.fromValue(body))
            .exchangeToMono(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    log.error("Credit Server Exception: " + response.statusCode());
                    throw new BadRequestException("크레딧 차감 실패");
                }

                return response.bodyToMono(new ParameterizedTypeReference<GlobalResponse<Void>>() {});
            }).block();
    }

    @Override
    public void transferCredit(TransferCreditRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("amount", request.getAmount());
        body.put("transactionDateTime", LocalDateTime.now());
        body.put("toUserId", request.getToUserId().toLong());

        webClient.post().uri(creditUrl+"/credit/transfer")
            .header("Authorization", String.format("Bearer %s", request.getToken()))
            .body(BodyInserters.fromValue(body))
            .exchangeToMono(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    log.error("Credit Server Exception: " + response.statusCode());
                    throw new BadRequestException("크레딧 이체 실패");
                }

                return response.bodyToMono(new ParameterizedTypeReference<GlobalResponse<Void>>() {});
            }).block();
    }

    @Override
    public void transfer(final TransferCashRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("amount", request.getAmount());
        body.put("fromAccountNumber", request.getFromAccountNumber());
        body.put("toAccountNumber", request.getToAccountNumber());

        webClient.post().uri(assetUrl+"/transfers")
            .header("front-social-id", request.getFrontId())
            .header("back-social-id", request.getBackId())
            .header("user-social-contact", request.getContact())
            .body(BodyInserters.fromValue(body))
            .exchangeToMono(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    throw new InternalServerException("송금 실패 " + response.statusCode());
                }

                return response.bodyToMono(new ParameterizedTypeReference<GlobalResponse<Void>>() {});
            }).block();
    }
}
