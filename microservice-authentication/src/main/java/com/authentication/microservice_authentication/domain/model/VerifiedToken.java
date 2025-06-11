package com.authentication.microservice_authentication.domain.model;

import lombok.Builder;
import lombok.Value;

@Value // Cria uma classe imutável com getters, equals, hashCode, etc.
@Builder
public class VerifiedToken {
    String uid;     // ID único do Firebase
    String name;
    String email;
    String picture;
}