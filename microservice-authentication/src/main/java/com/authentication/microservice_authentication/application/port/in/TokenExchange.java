package com.authentication.microservice_authentication.application.port.in;

import java.util.Optional;

import com.authentication.microservice_authentication.domain.model.VerifiedToken;


public interface TokenExchange {
    String generatedToken(VerifiedToken verifiedToken);
    VerifiedToken getUserByToken(String token); 
    Optional<VerifiedToken> validateToken(String token);
    
}
