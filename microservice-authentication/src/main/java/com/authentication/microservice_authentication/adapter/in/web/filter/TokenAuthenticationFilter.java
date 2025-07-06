package com.authentication.microservice_authentication.adapter.in.web.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authentication.microservice_authentication.application.service.TokenService;
import com.authentication.microservice_authentication.domain.model.VerifiedToken;
import com.authentication.microservice_authentication.domain.port.in.TokenVerificationPort;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private final TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        Optional<String> token = extractToken(request);

        token.ifPresent(authToken -> {
            Optional<VerifiedToken> verifiedTokenOpt = tokenService.validateToken(authToken);
            verifiedTokenOpt.ifPresent(verifiedToken -> {
                UserDetails userDetails = new User(verifiedToken.getUid(), "", Collections.emptyList());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        verifiedToken, 
                        null,          
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        });
        filterChain.doFilter(request, response);
    }

 
    private Optional<String> extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }
}