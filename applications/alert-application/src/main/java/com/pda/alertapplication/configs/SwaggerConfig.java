package com.pda.alertapplication.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(@Value("${service-url}") String url) {
        return new OpenAPI()
                .addServersItem(new Server().url(url))
                .info(new Info()
                        .title("알림 API")
                        .version("1.0")
                        .description("알림 서버의 API 명세서 입니다")
                        .contact(new Contact()
                                .name("김시은")
                                .email("se098300@gmail.com")));
    }
}
