package com.classroom.microsservice_classroom.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassroomDTO {
    
    @NotNull(message = "{error.invalid.object}")
    private Integer id;
    
    @NotBlank(message = "{error.invalid.object}")
    private String name;
    @NotBlank(message = "{error.invalid.object}")
    private String description;
    @NotBlank(message = "{error.invalid.object}")
    private String image;
    @NotBlank(message = "{error.invalid.object}")
    private String code;

    private List<StudentDTO> students;
    private List<SubjectDTO> subjects;
    
    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;

}
