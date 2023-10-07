package com.example.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", r -> r
                        .path("/user")
                        .uri("http://localhost:8082")
                )
                .route("user-update-delete-getId",r -> r
                        .path("/user/{id}")
                        .uri("http://localhost:8082")
                )
                .build();
    }
}
