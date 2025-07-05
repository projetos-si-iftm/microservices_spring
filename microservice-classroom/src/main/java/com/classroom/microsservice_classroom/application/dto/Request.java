package com.classroom.microsservice_classroom.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    
    @NotNull(message = "Requisição inválida")
    private Integer id;
    @NotBlank(message = "Requisição inválida")
    private String title;
    @NotBlank(message = "Requisição inválida")
    private String subtitle;
    @NotBlank(message = "Requisição inválida")
    private String background;
    @NotBlank(message = "Requisição inválida")
    private String theme;
    
    private boolean active;

}
