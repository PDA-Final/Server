package com.pda.productapplication.configs.swagger;

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
                .info(new Info()
                        .title("상품 API")
                        .version("1.0")
                        .description("상품 서버의 API 명세서 입니다")
                        .contact(new Contact()
                                .name("김시은")
                                .email("se098300@gmail.com")));
    }
}
