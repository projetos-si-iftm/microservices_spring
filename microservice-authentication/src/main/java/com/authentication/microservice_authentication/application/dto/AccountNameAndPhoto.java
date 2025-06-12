package com.authentication.microservice_authentication.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountNameAndPhoto {
    private String id;
    private String name;
    private String photo;
}
