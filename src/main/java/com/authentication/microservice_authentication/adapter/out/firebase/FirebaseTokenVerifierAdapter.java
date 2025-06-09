package com.authentication.microservice_authentication.adapter.out.firebase;


import com.authentication.microservice_authentication.domain.model.VerifiedToken;
import com.authentication.microservice_authentication.domain.port.in.TokenVerificationPort;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirebaseTokenVerifierAdapter implements TokenVerificationPort {

    private final FirebaseAuth firebaseAuth;

    @Override
    public Optional<VerifiedToken> verify(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            
            VerifiedToken verifiedToken = VerifiedToken.builder()
                .uid(decodedToken.getUid())
                .name(decodedToken.getName())
                .email(decodedToken.getEmail())
                .picture(decodedToken.getPicture())
                .build();
            
            return Optional.of(verifiedToken);
        } catch (FirebaseAuthException e) {
            log.error("Firebase token verification failed: {}", e.getMessage());
            return Optional.empty();
        }
    }
}