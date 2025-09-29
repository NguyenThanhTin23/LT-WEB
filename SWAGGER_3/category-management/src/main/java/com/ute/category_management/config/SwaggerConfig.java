package com.ute.category_management.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI categoryManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Category & Product API")
                        .description("Spring Boot CRUD example with Category and Product (AJAX + Swagger)")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub")
                        .url("https://github.com/your-repo-link"));
    }
}
