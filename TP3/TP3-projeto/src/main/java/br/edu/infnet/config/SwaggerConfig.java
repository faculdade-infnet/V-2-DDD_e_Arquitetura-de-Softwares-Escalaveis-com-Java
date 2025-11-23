package br.edu.infnet.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("TP3")
                        .version("1.0")
                        .description("Domain-Driven Design (DDD) e Arquitetura de Softwares Escaláveis com Java")
                )
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor local"));
    }
}
