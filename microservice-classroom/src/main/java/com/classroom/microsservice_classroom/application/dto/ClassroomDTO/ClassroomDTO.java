package com.classroom.microsservice_classroom.application.dto.ClassroomDTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassroomDTO {
    
    
    private String id;
    @NotBlank(message = "{error.invalid.object}")
    private String name;
    @NotBlank(message = "{error.invalid.object}")
    private String description;
    @NotBlank(message = "{error.invalid.object}")
    private String image;
    @NotBlank(message = "{error.invalid.object}")
    private String code;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime createIn;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime updateIn;
    private boolean active;

}
