package com.microservice.microservice_gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final WebClient.Builder webClientBuilder;
    private final List<String> publicEndpoints = List.of(
            "/auth/api/login/google",
            "/eureka");

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (isPublic(path)) {
            return chain.filter(exchange);
        }

        if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
            return unauthenticated(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().get("Authorization").get(0);
        System.out.println(authHeader);
        return webClientBuilder.build()
                .post()
                .uri("http://MICROSERVICE-AUTHENTICATION/auth/api/validate")
                .header("Authorization", authHeader)
                .retrieve()
                .toBodilessEntity()
                .flatMap(response -> {

                    if (response.getStatusCode().is2xxSuccessful()) {
                        return chain.filter(exchange);
                    }

                    System.out.println(response.getStatusCode());
                    return unauthenticated(exchange);
                })
                .onErrorResume(e -> unauthenticated(exchange));
    }

    private boolean isPublic(String path) {
        return publicEndpoints.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> unauthenticated(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}