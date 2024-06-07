package com.pda.apigateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info().title("ToFin API 명세서")
                .description("ToFin API 명세서입니다. \"Select a definition\"에서 원하는 API 명세서를 찾으시면 됩니다.")
                .version("1.0.0")
                .contact(new Contact()
                    .name("김동원")
                    .email("dongwon000103@gmail.com")));
    }
}
