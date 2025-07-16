package com.classroom.microsservice_classroom.application.dto.ClassroomDTO;

import java.time.LocalDateTime;
import java.util.List;

import com.classroom.microsservice_classroom.application.dto.SubjectDTO;
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
    private String code;
    private List<SubjectDTO> subjects;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime createIn;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime updateIn;
    private boolean active;

}
