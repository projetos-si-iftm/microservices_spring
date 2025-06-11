package com.authentication.microservice_authentication.application.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String googleId;
    private String name;
    private String email;
    private String photo;
    private String idToken; 
}
