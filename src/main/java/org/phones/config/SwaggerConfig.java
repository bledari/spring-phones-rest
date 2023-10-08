/**
 * @author Bledar B
 */
package org.phones.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {

        return new OpenAPI().components(new Components()).info(new Info()
            .title("REST Phones backend Api Documentation").version("1.0")
            .termsOfService("Phones backend terms of service")
            .description(
                "This is REST API documentation of the Spring Phones backend. If authentication is enabled, when calling the APIs use admin/admin")
        );
    }
}
