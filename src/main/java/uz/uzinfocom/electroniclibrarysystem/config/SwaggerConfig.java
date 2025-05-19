package uz.uzinfocom.electroniclibrarysystem.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String apiTitle;

    @Value("${spring.application.url}")
    private String apiUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle))
                .servers(List.of(new Server().url(apiUrl)))
                .components(new Components()
                        .addSecuritySchemes("access_token", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer"))
                )
                .security(Collections.singletonList(new SecurityRequirement().addList("access_token")));
    }
}