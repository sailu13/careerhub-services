package com.careerhub.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI careerHubOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("CareerHub API")
                        .description("Backend REST APIs for CareerHub")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sailaja Mende")
                                .email("sailajamende@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("CareerHub Documentation"));
    }
}