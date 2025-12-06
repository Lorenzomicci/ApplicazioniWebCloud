package com.progetto.web.cloud.progetto;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiDocumentation() {
        final String securitySchemeName = "bearer-jwt";

        return new OpenAPI()
            .info(new Info()
                .title("Progetto API")
                .description("Demo endpoints secured by Keycloak JWT"))
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .name(securitySchemeName)
                    .description("Provide the access token issued by Keycloak")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
}
