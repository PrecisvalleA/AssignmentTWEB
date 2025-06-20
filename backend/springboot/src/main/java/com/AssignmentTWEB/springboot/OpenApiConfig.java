package com.AssignmentTWEB.springboot;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger OpenAPI configuration for API documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie Full Stack Project")
                        .description("""
                            Project for Web Technologies assignment.

                            Modules documented here:
                            - Movies API (Spring Boot)
                            - Actors, Crews, Countries, Genres, Languages, Posters, Releases, Studios, Themes
                            - Oscar Awards integration

                            Full stack project includes also:
                            - Data Cleaning (Python + Jupiter Notebook)
                            - Main REST API Server (Axios + Express)
                            - Reviews Microservice (Node.js + Express)
                            - Frontend Client (React or Angular or Vue depending on your stack)
                        """)
                        .version("1.0.0")
                        .license(new License().name("MIT License")));
    }
}
