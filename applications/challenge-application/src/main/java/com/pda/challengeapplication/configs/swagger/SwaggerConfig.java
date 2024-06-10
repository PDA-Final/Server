package com.pda.challengeapplication.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(@Value("${service-url}") String url) {
        return new OpenAPI()
                .addServersItem(new Server().url(url))
                .addServersItem(new Server()
                        .description("챌린지 개발")
                        .url("http://localhost:8081"))
                .info(new Info()
                        .title("챌린지 API")
                        .version("1.0")
                        .description("챌린지 서버의 API 명세서 입니다")
                        .contact(new Contact()
                                .name("배별하")
                                .email("baestar0207@gmail.com")));
    }
}
