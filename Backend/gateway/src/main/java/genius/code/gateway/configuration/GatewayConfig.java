package genius.code.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/**")
                        .filters(f -> f.rewritePath("/api/(?<remaining>.*)", "/${remaining}"))
                        .uri("http://localhost:8080"))
                .build();
    }
}
