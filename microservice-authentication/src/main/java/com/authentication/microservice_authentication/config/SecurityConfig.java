
package com.authentication.microservice_authentication.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authentication.microservice_authentication.adapter.in.web.filter.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    // Define os endpoints que NÃO exigem autenticação.
    private static final String[] WHITELISTED_API_ENDPOINTS = {
        "/api/login/google",
        // Adicione aqui outros endpoints públicos, como documentação da API
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Desabilitar CSRF, pois usamos tokens e não sessões de navegador
            .csrf(csrf -> csrf.disable())

            // 2. Definir a política de sessão como STATELESS. O servidor não guarda estado de sessão.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // 3. Configurar as regras de autorização para cada endpoint
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(WHITELISTED_API_ENDPOINTS).permitAll() // Permite acesso aos endpoints da whitelist
                .anyRequest().authenticated() // Exige autenticação para todos os outros endpoints
            )

            // 4. Adicionar nosso filtro customizado ANTES do filtro padrão de username/password do Spring
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}