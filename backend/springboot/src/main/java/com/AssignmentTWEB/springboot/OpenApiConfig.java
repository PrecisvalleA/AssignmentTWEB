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
                        .title("IUM-TWEB UNITO PROJECT")
                        .description("""
                            Project for IUM-TWEB assignment.

                            Modules documented here:
                            - Movies API (Spring Boot): Actors, Crews, Countries, Genres, Languages, Posters, Releases, Studios, Themes, Oscar Awards

                            Project includes also:
                            - Data Cleaning and Analysis(Python + Jupiter Notebook)
                            - Main Server (Axios + Express)
                            - Reviews Server (Node.js + Express)
                            - Frontend Client (HTML + Javascript + Css + Bootstrap)
                        """)
                        .version("1.0.0")
                        .license(new License().name("MIT License")));
    }
}
