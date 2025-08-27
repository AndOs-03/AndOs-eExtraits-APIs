package com.andos.eextraits.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Configuration personalisé de la page swagger.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("AndOs eExtraits APIs")
            .version("v1.0")
            .description("Documentation des APIs de AndOs eExtraits")
            .contact(new Contact()
                .name("Anderson Ouattara")
                .email("andrson1er@gmail.com")));
  }
}