package com.authentication.microservice_authentication.domain.port.in;


import java.util.Optional;

import com.authentication.microservice_authentication.domain.model.VerifiedToken;

public interface TokenVerificationPort {
    /**
     * Verifica um ID token e retorna os detalhes do usuário se for válido.
     * @param token O ID Token (JWT) a ser verificado.
     * @return Um Optional contendo os detalhes do token verificado, ou vazio se for inválido.
     */
    Optional<VerifiedToken> verify(String token);
}