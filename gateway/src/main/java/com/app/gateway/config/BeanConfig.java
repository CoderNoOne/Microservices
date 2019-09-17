package com.app.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder
                .routes()

                .route(r -> r.path("/api/loanapplications/customers/**")
                        .filters(f -> f.rewritePath("/api/loanapplications/customers/(?<segment>.*)", "/api/loanapplications/customers/${segment}"))
                        .uri("lb://loan-application-microservice/")
                        .id("loan-application-microservice"))

                .route(r -> r.path("/api/loanapplications/**")
                        .filters(f -> f.rewritePath("/api/loanapplications/(.*)?", "/${remains}"))
                        .uri("lb://loan-application-microservice/")
                        .id("loan-application-microservice"))


                .route(r -> r.path("/api/customers/**")
                        .filters(f -> f.rewritePath("/api/customers/(?<segment>.*)", "/api/customers/${segment}"))
                        .uri("lb://customer-microservice/")
                        .id("customer-microservice"))
                .build();
    }
}
