package com.minhhieu.gatewayservice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator gatewayRouters(RouteLocatorBuilder builder){
        return builder.routes()
                .route("book-service",r -> r.path("/book/**").filters(f -> f.circuitBreaker(c -> c.setName("nameCircuit").setFallbackUri("forward:/fallback/message"))).uri("lb://book-service"))
                .route("borrow-service",r -> r.path("/borrow/**").filters(f -> f.circuitBreaker(c -> c.setName("nameCircuit").setFallbackUri("forward:/fallback/message"))).uri("lb://borrow-service"))
                .route("front-service",r -> r.path("/front/**").filters(f -> f.circuitBreaker(c -> c.setName("nameCircuit").setFallbackUri("forward:/fallback/message"))).uri("lb://front-service"))
                .route("user-service", r -> r.path("/user/**").filters(f -> f.circuitBreaker(c -> c.setName("nameCircuit").setFallbackUri("forward:/fallback/message"))).uri("lb://user-service"))
                .build();

    }
}
