package com.authentication.microservice_authentication.application.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
    private String id;
    private String name;
    private String email;
    private String photo;
    private String role;
    private Boolean active;
}