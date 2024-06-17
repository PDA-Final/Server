package com.pda.userapplication.utils.outapi;

import com.pda.apiutils.GlobalResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@DisplayName("test")
class TestWebClientTest {
    @Test
    @DisplayName("API 테스트 실행")
    void test() {
        WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:7979")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        Mono<GlobalResponse<Object>> response = webClient.get()
            .uri("/assets")
            .header("front-social-id", "000103")
            .header("back-social-id", "1234123")
            .header("user-social-contact", "01025300757")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<GlobalResponse<Object>>() {});

        System.out.println(response.block().getMessage());

    }
}