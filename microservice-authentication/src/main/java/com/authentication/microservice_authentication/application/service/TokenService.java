package com.authentication.microservice_authentication.application.service;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.authentication.microservice_authentication.application.port.in.TokenExchange;
import com.authentication.microservice_authentication.domain.model.VerifiedToken;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class TokenService implements TokenExchange {

    @Value("${app_secret}")
    private String secret;
    private static final String ISSUER = "api_security";

    /**
     * Gera um novo token JWT para a aplicação com base nos dados do usuário
     * verificado.
     * Os claims (informações) importantes são armazenados diretamente no token.
     */
    @Override
    public String generatedToken(VerifiedToken verifiedToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(verifiedToken.getUid()) 
                    .withClaim("email", verifiedToken.getEmail()) 
                    .withClaim("name", verifiedToken.getName()) 
                    .withClaim("picture", verifiedToken.getPicture())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    /**
     * Valida um token e retorna o DecodedJWT se for válido.
     * Lança JWTVerificationException se a validação falhar.
     * Este método agora é o ponto central para validação e extração de dados.
     *
     * @param token O token JWT a ser validado.
     * @return O token decodificado e VERIFICADO.
     * @throws JWTVerificationException se o token for inválido, expirado ou
     *                                  malformado.
     */
    private DecodedJWT verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();

        return verifier.verify(token); 
    }

    
    @Override
    public Optional<VerifiedToken> validateToken(String token) {
        try {
              DecodedJWT decodedJWT = verifyToken(token);

            String uid = decodedJWT.getSubject();
            String email = decodedJWT.getClaim("email").asString();
            String name = decodedJWT.getClaim("name").asString();
            String picture = decodedJWT.getClaim("picture").asString();
         VerifiedToken verifiedToken = VerifiedToken.builder()
                    .uid(uid)
                    .name(name)
                    .email(email)
                    .picture(picture)
                    .build();
                      return Optional.of(verifiedToken);
        } catch (JWTVerificationException exception) {
         
            log.warn("Falha na validação do JWT: {}. Token: {}", exception.getMessage(), token);

            return Optional.empty();
        }
    }


    @Override
    public VerifiedToken getUserByToken(String token) {
        try {
            DecodedJWT decodedJWT = verifyToken(token);

            String uid = decodedJWT.getSubject();
            String email = decodedJWT.getClaim("email").asString();
            String name = decodedJWT.getClaim("name").asString();
            String picture = decodedJWT.getClaim("picture").asString();
            return VerifiedToken.builder()
                    .uid(uid)
                    .name(name)
                    .email(email)
                    .picture(picture)
                    .build();

        } catch (JWTVerificationException e) {
            throw new SecurityException("Token inválido ou expirado.", e);
        }
    }

    private Instant genExpirationDate() {

        return ZonedDateTime.now(ZoneOffset.of("-03:00"))
                .plusHours(12)
                .toInstant();
    }

}
