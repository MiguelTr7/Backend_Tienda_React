package com.example.Tienda_React.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tiendaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ferretería React API")
                        .description("API REST para la tienda online con autenticación JWT y roles")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Miguel Tropa")
                                .email("aurorasdelsur@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
