package com.andos.eextraits.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Configuration personalisé de la page swagger.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Configuration
public class SwaggerConfig {

  private static final String SECURITY_SCHEME_NAME = "bearerAuth";

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("AndOs eExtraits APIs")
            .version("v1.0")
            .description("Documentation des APIs de AndOs eExtraits")
            .contact(new Contact()
                .name("Anderson Ouattara")
                .email("andrson1er@gmail.com")))
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        .components(new Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME,
                new SecurityScheme()
                    .name(SECURITY_SCHEME_NAME)
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )
        );
  }
}